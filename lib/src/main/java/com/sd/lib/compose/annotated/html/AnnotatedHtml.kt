package com.sd.lib.compose.annotated.html

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sd.lib.compose.annotated.html.tags.Tag_a
import com.sd.lib.compose.annotated.html.tags.Tag_b
import com.sd.lib.compose.annotated.html.tags.Tag_br
import com.sd.lib.compose.annotated.html.tags.Tag_div
import com.sd.lib.compose.annotated.html.tags.Tag_em
import com.sd.lib.compose.annotated.html.tags.Tag_font
import com.sd.lib.compose.annotated.html.tags.Tag_heading
import com.sd.lib.compose.annotated.html.tags.Tag_i
import com.sd.lib.compose.annotated.html.tags.Tag_p
import com.sd.lib.compose.annotated.html.tags.Tag_strong
import com.sd.lib.compose.annotated.html.tags.Tag_u
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import org.jsoup.parser.ParseSettings
import org.jsoup.parser.Parser

open class AnnotatedHtml {
   private val _tags = mutableMapOf<String, () -> Tag>()
   private val _inlineTextContentFlow = MutableStateFlow<Map<String, InlineTextContent>>(emptyMap())

   val inlineTextContentFlow: StateFlow<Map<String, InlineTextContent>>
      get() = _inlineTextContentFlow.asStateFlow()

   fun parse(
      html: String,
      parser: Parser = Parser.htmlParser().settings(ParseSettings.preserveCase),
   ): AnnotatedString {
      val body = Jsoup.parse(html, parser).body()
      return buildAnnotatedString {
         parseElement(body, null)
      }
   }

   fun addTag(tag: String, factory: () -> Tag) {
      _tags[tag] = factory
   }

   init {
      addTag("a") { Tag_a() }
      addTag("b") { Tag_b() }
      addTag("br") { Tag_br() }
      addTag("div") { Tag_div() }
      addTag("em") { Tag_em() }
      addTag("font") { Tag_font() }
      addTag("h1") { Tag_heading(style = SpanStyle(fontSize = 32.sp)) }
      addTag("h2") { Tag_heading(style = SpanStyle(fontSize = 28.sp)) }
      addTag("h3") { Tag_heading(style = SpanStyle(fontSize = 24.sp)) }
      addTag("h4") { Tag_heading(style = SpanStyle(fontSize = 20.sp)) }
      addTag("h5") { Tag_heading(style = SpanStyle(fontSize = 18.sp)) }
      addTag("h6") { Tag_heading(style = SpanStyle(fontSize = 16.sp)) }
      addTag("i") { Tag_i() }
      addTag("p") { Tag_p() }
      addTag("strong") { Tag_strong() }
      addTag("u") { Tag_u() }
   }

   private fun AnnotatedString.Builder.parseElement(element: Element, tagBuilder: Tag?) {
      for (node in element.childNodes()) {
         when (node) {
            is TextNode -> {
               if (tagBuilder != null) {
                  tagBuilder.buildText(
                     builder = this,
                     text = node.text()
                  )
               } else {
                  append(node.text())
               }
            }

            is Element -> {
               val tag = node.tagName()
               val builder = getBuilder(tag)

               builder?.beforeElement(
                  element = node,
                  builder = this,
               )

               val start = length
               parseElement(node, builder)
               val end = length

               builder?.afterElement(
                  element = node,
                  builder = this,
                  start = start,
                  end = end,
               )
            }
         }
      }
   }

   private fun getBuilder(tag: String): Tag? {
      return _tags[tag]?.invoke()?.also { tagBuilder ->
         tagBuilder.inlineTextContentHolder = _inlineTextContentHolder
      }
   }

   private val _inlineTextContentHolder = object : InlineTextContentHolder {
      override fun addInlineTextContent(
         id: String,
         placeholder: Placeholder,
         content: @Composable (String) -> Unit,
      ) {
         _inlineTextContentFlow.update {
            it + (id to InlineTextContent(placeholder, content))
         }
      }
   }

   abstract class Tag {
      internal lateinit var inlineTextContentHolder: InlineTextContentHolder

      protected fun addInlineTextContent(
         id: String,
         placeholderWidth: TextUnit = 1.em,
         placeholderHeight: TextUnit = 1.em,
         placeholderVerticalAlign: PlaceholderVerticalAlign = PlaceholderVerticalAlign.TextBottom,
         content: @Composable (String) -> Unit,
      ) {
         addInlineTextContent(
            id = id,
            placeholder = Placeholder(
               width = placeholderWidth,
               height = placeholderHeight,
               placeholderVerticalAlign = placeholderVerticalAlign,
            ),
            content = content,
         )
      }

      protected fun addInlineTextContent(
         id: String,
         placeholder: Placeholder,
         content: @Composable (String) -> Unit,
      ) {
         inlineTextContentHolder.addInlineTextContent(
            id = id,
            placeholder = placeholder,
            content = content,
         )
      }

      open fun beforeElement(
         element: Element,
         builder: AnnotatedString.Builder,
      ) = Unit

      open fun buildText(
         builder: AnnotatedString.Builder,
         text: String,
      ) {
         builder.append(text)
      }

      open fun afterElement(
         element: Element,
         builder: AnnotatedString.Builder,
         start: Int, end: Int,
      ) = Unit
   }
}

internal interface InlineTextContentHolder {
   fun addInlineTextContent(
      id: String,
      placeholder: Placeholder,
      content: @Composable (String) -> Unit,
   )
}
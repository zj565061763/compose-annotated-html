package com.sd.lib.compose.annotated.html

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import com.sd.lib.compose.annotated.html.tags.TagBuilder
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
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode

open class AnnotatedHtml {
   private val _tags = mutableMapOf<String, () -> TagBuilder>()

   fun parse(html: String): AnnotatedString {
      val body = Jsoup.parse(html).body()
      return buildAnnotatedString {
         parseElement(body, null)
      }
   }

   fun addBuilder(tag: String, factory: () -> TagBuilder) {
      _tags[tag] = factory
   }

   init {
      addBuilder("a") { Tag_a() }
      addBuilder("b") { Tag_b() }
      addBuilder("br") { Tag_br() }
      addBuilder("div") { Tag_div() }
      addBuilder("em") { Tag_em() }
      addBuilder("font") { Tag_font() }
      addBuilder("h1") { Tag_heading(style = SpanStyle(fontSize = 32.sp)) }
      addBuilder("h2") { Tag_heading(style = SpanStyle(fontSize = 28.sp)) }
      addBuilder("h3") { Tag_heading(style = SpanStyle(fontSize = 24.sp)) }
      addBuilder("h4") { Tag_heading(style = SpanStyle(fontSize = 20.sp)) }
      addBuilder("h5") { Tag_heading(style = SpanStyle(fontSize = 18.sp)) }
      addBuilder("h6") { Tag_heading(style = SpanStyle(fontSize = 16.sp)) }
      addBuilder("i") { Tag_i() }
      addBuilder("p") { Tag_p() }
      addBuilder("strong") { Tag_strong() }
      addBuilder("u") { Tag_u() }
   }

   private fun AnnotatedString.Builder.parseElement(element: Element, tagBuilder: TagBuilder?) {
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
                  node = node,
                  builder = this,
               )

               val start = length
               parseElement(node, builder)
               val end = length

               builder?.afterElement(
                  node = node,
                  builder = this,
                  start = start,
                  end = end,
               )
            }
         }
      }
   }

   private fun getBuilder(tag: String): TagBuilder? = _tags[tag]?.invoke()
}
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_a(
   private val style: SpanStyle = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
   private val linkInteractionListener: LinkInteractionListener? = null,
) : AnnotatedHtml.Tag() {
   override fun elementEnd(builder: AnnotatedString.Builder, element: Element, start: Int, end: Int) {
      val href = element.attr("href")
      val linkAnnotation = LinkAnnotation.Url(
         url = href,
         styles = TextLinkStyles(style),
         linkInteractionListener = linkInteractionListener,
      )
      builder.addLink(
         url = linkAnnotation,
         start = start,
         end = end,
      )
   }
}
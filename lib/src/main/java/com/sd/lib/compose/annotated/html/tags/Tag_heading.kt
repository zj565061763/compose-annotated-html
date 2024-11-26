package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_heading(
   private val style: SpanStyle,
) : AnnotatedHtml.Tag() {

   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }

   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.addStyle(
         style = style,
         start = start,
         end = end,
      )
   }
}
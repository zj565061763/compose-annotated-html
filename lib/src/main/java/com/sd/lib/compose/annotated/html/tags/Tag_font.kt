package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.styleColor
import org.jsoup.nodes.Element

open class Tag_font : AnnotatedHtml.Tag() {
   override fun elementEnd(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      val color = element.styleColor()
      if (color != null) {
         builder.addStyle(
            style = SpanStyle(color = color),
            start = start,
            end = end,
         )
      }
   }
}
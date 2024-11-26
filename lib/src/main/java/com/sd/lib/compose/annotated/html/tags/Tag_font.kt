package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.styleColor
import org.jsoup.nodes.Element

open class Tag_font : AnnotatedHtml.Tag() {
   override fun elementEnd(builder: AnnotatedString.Builder, element: Element, start: Int, end: Int) {
      element.styleColor()?.also { value ->
         builder.addStyle(
            style = SpanStyle(color = value),
            start = start,
            end = end,
         )
      }
   }
}
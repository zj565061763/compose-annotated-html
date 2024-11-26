package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_u : AnnotatedHtml.Tag() {
   override fun elementEnd(builder: AnnotatedString.Builder, element: Element, start: Int, end: Int) {
      builder.addStyle(
         style = SpanStyle(textDecoration = TextDecoration.Underline),
         start = start,
         end = end,
      )
   }
}
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import org.jsoup.nodes.Element

open class Tag_b : TagBuilder() {
   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.addStyle(
         style = SpanStyle(fontWeight = FontWeight.Bold),
         start = start,
         end = end,
      )
   }
}
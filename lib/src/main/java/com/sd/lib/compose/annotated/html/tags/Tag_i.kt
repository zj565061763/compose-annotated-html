package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_i : AnnotatedHtml.Tag() {
   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.addStyle(
         style = SpanStyle(fontStyle = FontStyle.Italic),
         start = start,
         end = end,
      )
   }
}
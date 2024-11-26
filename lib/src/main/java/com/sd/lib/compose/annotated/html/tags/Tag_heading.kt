package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import org.jsoup.nodes.Element

open class Tag_heading(
   private val style: SpanStyle,
) : TagBlock() {

   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      super.afterElement(element, builder, start, end)
      builder.addStyle(
         style = style,
         start = start,
         end = end,
      )
   }
}
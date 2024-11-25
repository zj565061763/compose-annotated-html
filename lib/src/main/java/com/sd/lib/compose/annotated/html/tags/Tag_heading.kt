package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import org.jsoup.nodes.Node

open class Tag_heading(
   private val style: SpanStyle,
) : TagBuilder() {

   override fun beforeElement(node: Node, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }

   override fun afterElement(node: Node, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.addStyle(
         style = style,
         start = start,
         end = end,
      )
   }
}
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Node

open class Tag_u : TagBuilder() {
   override fun afterElement(node: Node, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.addStyle(
         style = SpanStyle(textDecoration = TextDecoration.Underline),
         start = start,
         end = end,
      )
   }
}
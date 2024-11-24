package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Node

open class Tag_div : TagBuilder() {
   override fun beforeElement(node: Node, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }

   override fun afterElement(node: Node, builder: AnnotatedString.Builder, start: Int, end: Int) {
      builder.append("\n")
   }
}
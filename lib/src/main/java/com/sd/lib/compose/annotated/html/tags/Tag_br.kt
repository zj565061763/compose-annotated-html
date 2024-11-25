package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Node

open class Tag_br : TagBuilder() {
   override fun beforeElement(node: Node, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }
}
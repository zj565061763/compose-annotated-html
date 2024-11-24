package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Node

open class Tag_br : TagBuilder() {
   override fun buildText(node: Node, builder: AnnotatedString.Builder, text: String) {
      builder.append("\n")
   }
}
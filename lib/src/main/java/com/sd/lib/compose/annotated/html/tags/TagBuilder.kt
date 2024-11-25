package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Node

abstract class TagBuilder {
   open fun buildText(
      builder: AnnotatedString.Builder,
      text: String,
   ) {
      builder.append(text)
   }

   open fun beforeElement(
      node: Node,
      builder: AnnotatedString.Builder,
   ) = Unit

   open fun afterElement(
      node: Node,
      builder: AnnotatedString.Builder,
      start: Int, end: Int,
   ) = Unit
}
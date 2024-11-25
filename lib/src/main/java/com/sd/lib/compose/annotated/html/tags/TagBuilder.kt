package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Element

abstract class TagBuilder {
   open fun buildText(
      builder: AnnotatedString.Builder,
      text: String,
   ) {
      builder.append(text)
   }

   open fun beforeElement(
      element: Element,
      builder: AnnotatedString.Builder,
   ) = Unit

   open fun afterElement(
      element: Element,
      builder: AnnotatedString.Builder,
      start: Int, end: Int,
   ) = Unit
}
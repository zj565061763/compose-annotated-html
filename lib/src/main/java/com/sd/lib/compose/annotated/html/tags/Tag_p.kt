package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Element

open class Tag_p : TagBuilder() {
   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }
}
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import org.jsoup.nodes.Element

open class Tag_li : TagBlock() {
   override fun elementStart(builder: AnnotatedString.Builder, element: Element) {
      super.elementStart(builder, element)
      builder.append("•")
   }
}
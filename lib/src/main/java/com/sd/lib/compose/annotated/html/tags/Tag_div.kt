package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_div : AnnotatedHtml.Tag() {
   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }
}
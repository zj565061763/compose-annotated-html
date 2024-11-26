package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

open class Tag_br : AnnotatedHtml.Tag() {
   override fun elementStart(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }
}
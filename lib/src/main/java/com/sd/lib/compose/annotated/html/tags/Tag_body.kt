package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode

class Tag_body : AnnotatedHtml.Tag() {
   override fun elementText(builder: AnnotatedString.Builder, element: Element, textNode: TextNode) {
      if (textNode.siblingIndex() == 0 && textNode.text().isBlank()) {
         // Ignore
      } else {
         super.elementText(builder, element, textNode)
      }
   }
}
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.style.TextAlign
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.style
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node

open class TagBlock : AnnotatedHtml.Tag() {
   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }

   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
      val textAlign = when (element.getTextAlign()) {
         "start" -> TextAlign.Start
         "center" -> TextAlign.Center
         "end" -> TextAlign.End
         else -> null
      }
      if (textAlign != null) {
         builder.addStyle(
            style = ParagraphStyle(textAlign = textAlign),
            start = start,
            end = end,
         )
      }
   }
}

private fun Node.getTextAlign(): String? {
   val style = style()
   if (style.isBlank()) return null

   val result = sTextAlignRegex.find(style) ?: return null
   return result.groups[1]?.value
}

private val sTextAlignRegex = """(?:\s+|\A)text-align\s*:\s*(\S*)\b""".toRegex()
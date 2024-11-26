package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.style
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node

open class TagBlock : AnnotatedHtml.Tag() {
   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      builder.append("\n")
   }

   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
//      val textAlign = when (element.getTextAlign()) {
//         "left" -> TextAlign.Left
//         "right" -> TextAlign.Right
//         "center" -> TextAlign.Center
//         "justify" -> TextAlign.Justify
//         "start" -> TextAlign.Start
//         "end" -> TextAlign.End
//         else -> TextAlign.Unspecified
//      }
//      builder.addStyle(
//         style = ParagraphStyle(textAlign = textAlign),
//         start = start,
//         end = end,
//      )
   }
}

private fun Node.getTextAlign(): String? {
   val style = style()
   if (style.isBlank()) return null

   val result = sTextAlignRegex.find(style) ?: return null
   return result.groups[1]?.value
}

private val sTextAlignRegex = """(?:\s+|\A)text-align\s*:\s*(\S*)\b""".toRegex()
package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.sd.lib.compose.annotated.html.AnnotatedHtml
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

      val textDecoration = when (element.getTextDecoration()) {
         "underline" -> TextDecoration.Underline
         "line-through" -> TextDecoration.LineThrough
         else -> null
      }

      if (textDecoration != null) {
         builder.addStyle(
            style = SpanStyle(textDecoration = textDecoration),
            start = start,
            end = end,
         )
      }
   }
}

private fun Node.getTextAlign(): String? {
   val style = attr("style")
   if (style.isBlank()) return null

   val result = sTextAlignRegex.find(style) ?: return null
   return result.groups[1]?.value
}

private fun Node.getTextDecoration(): String? {
   val style = attr("style")
   if (style.isBlank()) return null

   val result = sTextDecorationRegex.find(style) ?: return null
   return result.groups[1]?.value
}

private val sTextAlignRegex = """(?:\s+|\A)text-align\s*:\s*(\S*)\b""".toRegex()
private val sTextDecorationRegex = """(?:\s+|\A)text-decoration\s*:\s*(\S*)\b""".toRegex()
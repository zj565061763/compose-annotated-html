package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.styleBackgroundColor
import com.sd.lib.compose.annotated.html.styleColor
import com.sd.lib.compose.annotated.html.styleTextDecoration
import org.jsoup.nodes.Element

open class TagBlock : AnnotatedHtml.Tag() {
   override fun elementStart(builder: AnnotatedString.Builder, element: Element) {
      builder.appendNewLineIfNeed()
   }

   override fun elementEnd(builder: AnnotatedString.Builder, element: Element, start: Int, end: Int) {
//      element.styleTextAlign()?.also { value ->
//         builder.addStyle(
//            style = ParagraphStyle(textAlign = value),
//            start = start,
//            end = end,
//         )
//      }

      element.styleColor()?.also { value ->
         builder.addStyle(
            style = SpanStyle(color = value),
            start = start,
            end = end,
         )
      }

      element.styleBackgroundColor()?.also { value ->
         builder.addStyle(
            style = SpanStyle(background = value),
            start = start,
            end = end,
         )
      }

      element.styleTextDecoration()?.also { value ->
         builder.addStyle(
            style = SpanStyle(textDecoration = value),
            start = start,
            end = end,
         )
      }

      builder.appendNewLineIfNeed()
   }
}

private fun AnnotatedString.Builder.appendNewLineIfNeed() {
   val text = toAnnotatedString().text
   if (text.isEmpty()) return

   for (i in text.lastIndex downTo 0) {
      val char = text[i]
      if (char == '\n') return
      if (char != ' ') break
   }

   appendLine()
}
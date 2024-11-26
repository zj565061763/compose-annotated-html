package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import com.sd.lib.compose.annotated.html.styleBackgroundColor
import com.sd.lib.compose.annotated.html.styleColor
import com.sd.lib.compose.annotated.html.styleTextDecoration
import org.jsoup.nodes.Element

open class TagBlock : AnnotatedHtml.Tag() {
   override fun beforeElement(element: Element, builder: AnnotatedString.Builder) {
      val text = builder.toAnnotatedString().text
      if (text.isNotBlank()) {
         val last = text.last().toString()
         if (last != "\n") {
            builder.append("\n")
         }
      }
   }

   override fun afterElement(element: Element, builder: AnnotatedString.Builder, start: Int, end: Int) {
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
   }
}

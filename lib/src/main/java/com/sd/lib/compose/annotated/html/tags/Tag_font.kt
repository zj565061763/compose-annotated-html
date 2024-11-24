package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.sd.lib.compose.annotated.html.libToColor
import org.jsoup.nodes.Node

open class Tag_font : TagBuilder() {
   override fun afterElement(node: Node, builder: AnnotatedString.Builder, start: Int, end: Int) {
      val color = node.attr("color").libToColor()
      if (color != null) {
         builder.addStyle(
            style = SpanStyle(color = color),
            start = start,
            end = end,
         )
      }
   }
}
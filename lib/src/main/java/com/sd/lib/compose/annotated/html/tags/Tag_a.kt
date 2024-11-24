package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Node

open class Tag_a(
   private val style: SpanStyle = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
   private val linkInteractionListener: LinkInteractionListener? = null,
) : TagBuilder() {
   private var _pushLinkIndex: Int? = null

   override fun beforeElement(node: Node, builder: AnnotatedString.Builder) {
      val href = node.attr("href")
      val linkAnnotation = LinkAnnotation.Url(
         url = href,
         styles = TextLinkStyles(style),
         linkInteractionListener = linkInteractionListener,
      )
      _pushLinkIndex = builder.pushLink(linkAnnotation)
   }

   override fun afterElement(node: Node, builder: AnnotatedString.Builder, start: Int, end: Int) {
      _pushLinkIndex?.let {
         _pushLinkIndex = null
         builder.pop(it)
      }
   }
}
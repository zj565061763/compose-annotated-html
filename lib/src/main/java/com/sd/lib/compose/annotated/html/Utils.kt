package com.sd.lib.compose.annotated.html

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Node

internal fun Node.styleTextAlign(): TextAlign? {
   val result = sTextAlignRegex.find(style()) ?: return null
   return when (result.groups[1]?.value) {
      "left" -> TextAlign.Left
      "right" -> TextAlign.Right
      "center" -> TextAlign.Center
      "justify" -> TextAlign.Justify
      "start" -> TextAlign.Start
      "end" -> TextAlign.End
      else -> null
   }
}

internal fun Node.styleColor(): Color? {
   val result = sColorRegex.find(style()) ?: return null
   return result.groups[1]?.value.toComposeColor()
}

internal fun Node.styleBackgroundColor(): Color? {
   val result = sBackgroundColorRegex.find(style()) ?: return null
   return result.groups[1]?.value.toComposeColor()
}

internal fun Node.styleTextDecoration(): TextDecoration? {
   val result = sTextDecorationRegex.find(style()) ?: return null
   return when (result.groups[1]?.value) {
      "underline" -> TextDecoration.Underline
      "line-through" -> TextDecoration.LineThrough
      else -> null
   }
}

private fun Node.style(): String = attr("style") ?: ""

private fun String?.toComposeColor(): Color? {
   if (isNullOrBlank()) return null
   sColorMap[this]?.also { return it }
   return kotlin.runCatching {
      Color(android.graphics.Color.parseColor(this@toComposeColor))
   }.getOrNull()
}

private val sColorMap = mapOf(
   "darkgray" to Color(0xFFA9A9A9),
   "gray" to Color(0xFF808080),
   "lightgray" to Color(0xFFD3D3D3),
   "darkgrey" to Color(0xFFA9A9A9),
   "grey" to Color(0xFF808080),
   "lightgrey" to Color(0xFFD3D3D3),
   "green" to Color(0xFF008000),
)

private val sTextAlignRegex = getStyleRegex("text-align").toRegex()
private val sColorRegex = getStyleRegex("color").toRegex()
private val sBackgroundColorRegex = getStyleRegex("background(?:-color)?").toRegex()
private val sTextDecorationRegex = getStyleRegex("text-decoration").toRegex()

private fun getStyleRegex(style: String): String {
   return "(?:\\s+|\\A)$style\\s*:\\s*(\\S*)\\b"
}
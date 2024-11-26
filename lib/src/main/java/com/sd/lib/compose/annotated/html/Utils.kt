package com.sd.lib.compose.annotated.html

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Node

internal fun Node.styleTextAlign(): String? {
   val result = sTextAlignRegex.find(style()) ?: return null
   return result.groups[1]?.value
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

private fun String?.toComposeColor(): Color? {
   if (isNullOrBlank()) return null
   return kotlin.runCatching {
      Color(android.graphics.Color.parseColor(this@toComposeColor))
   }.getOrNull()
}

private fun Node.style(): String = attr("style") ?: ""

private val sTextAlignRegex = getStyleRegex("text-align").toRegex()
private val sColorRegex = getStyleRegex("color").toRegex()
private val sBackgroundColorRegex = getStyleRegex("background(?:-color)?").toRegex()
private val sTextDecorationRegex = getStyleRegex("text-decoration").toRegex()

private fun getStyleRegex(style: String): String {
   return "(?:\\s+|\\A)$style\\s*:\\s*(\\S*)\\b"
}
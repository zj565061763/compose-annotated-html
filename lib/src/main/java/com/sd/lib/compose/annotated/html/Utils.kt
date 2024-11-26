package com.sd.lib.compose.annotated.html

import androidx.compose.ui.graphics.Color
import org.jsoup.nodes.Node

internal fun String.libToColor(): Color? {
   return runCatching {
      Color(android.graphics.Color.parseColor(this@libToColor))
   }.getOrNull()
}

internal fun Node.style(): String = attr("style")
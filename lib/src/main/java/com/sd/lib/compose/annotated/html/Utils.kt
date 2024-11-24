package com.sd.lib.compose.annotated.html

import androidx.compose.ui.graphics.Color

internal fun String.libToColor(): Color? {
   return runCatching {
      Color(android.graphics.Color.parseColor(this@libToColor))
   }.getOrNull()
}
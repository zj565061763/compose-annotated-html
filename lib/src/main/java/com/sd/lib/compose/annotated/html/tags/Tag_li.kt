package com.sd.lib.compose.annotated.html.tags

import androidx.compose.ui.text.AnnotatedString

open class Tag_li : TagBlock() {
   override fun buildText(builder: AnnotatedString.Builder, text: String) {
      builder.append("•")
      super.buildText(builder, text)
   }
}
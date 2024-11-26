package com.sd.demo.compose.annotated.html

import android.text.Html
import android.widget.TextView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.sd.lib.compose.annotated.html.AnnotatedHtml

@Composable
fun AppTextView(
   modifier: Modifier = Modifier,
   html: String,
) {
   val annotated = remember(html) { AnnotatedHtml().parse(html) }
   Text(
      modifier = modifier,
      text = annotated,
      fontSize = 14.sp,
      color = Color.Black,
   )
}

@Composable
fun AndroidTextView(
   modifier: Modifier = Modifier,
   html: String,
) {
   val spanned = remember(html) {
      Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT or Html.FROM_HTML_OPTION_USE_CSS_COLORS)
   }
   AndroidView(
      modifier = modifier,
      factory = { context ->
         TextView(context).apply {
            this.textSize = 14f
            this.setTextColor(android.graphics.Color.BLACK)
         }
      },
      update = { textView ->
         textView.text = spanned
      }
   )
}

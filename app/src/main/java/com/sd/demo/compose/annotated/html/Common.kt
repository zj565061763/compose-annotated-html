package com.sd.demo.compose.annotated.html

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

@Composable
fun AppTextView(
   modifier: Modifier = Modifier,
   html: String,
) {
   val annotatedHtml = remember { AppAnnotatedHtml() }
   val annotated = remember(annotatedHtml, html) { annotatedHtml.parse(html) }
   Text(
      modifier = modifier,
      text = annotated,
      fontSize = 14.sp,
      color = Color.Black,
      inlineContent = annotatedHtml.inlineTextContentFlow.collectAsStateWithLifecycle().value,
   )
}

private class AppAnnotatedHtml : AnnotatedHtml() {
   init {
      addTag("img") { AppTag_img() }
   }
}

private class AppTag_img : AnnotatedHtml.Tag() {
   override fun elementStart(element: Element, builder: AnnotatedString.Builder) {
      val src = element.attr("src")
      val alt = element.attr("alt")
      builder.appendInlineContent(id = src)
      addInlineTextContent(
         id = src,
         placeholderWidth = 50.sp,
         placeholderHeight = 50.sp,
         placeholderVerticalAlign = PlaceholderVerticalAlign.Center,
         content = {
            Box(
               contentAlignment = Alignment.Center,
               modifier = Modifier
                  .fillMaxSize()
                  .background(Color.Red.copy(0.2f))
            ) {
               Image(
                  painter = painterResource(R.drawable.cn),
                  contentDescription = alt,
                  contentScale = ContentScale.FillWidth,
                  modifier = Modifier
                     .background(Color.Green.copy(0.2f))
                     .fillMaxWidth(),
               )
            }
         },
      )
   }
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

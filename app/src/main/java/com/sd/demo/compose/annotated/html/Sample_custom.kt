package com.sd.demo.compose.annotated.html

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sd.demo.compose.annotated.html.theme.AppTheme
import com.sd.lib.compose.annotated.html.AnnotatedHtml
import org.jsoup.nodes.Element

class Sample_custom : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         AppTheme {
            Content()
         }
      }
   }
}

@Composable
private fun Content(
   modifier: Modifier = Modifier,
) {
   val html = """
        <p>start <user>hello</user> end</p>
    """.trimIndent()

   val annotatedHtml = remember { AnnotatedHtml() }
   val annotated = remember(html) {
      annotatedHtml
         .apply { addTag("user") { Tag_user() } }
         .parse(html)
   }

   val inlineContent by annotatedHtml.inlineTextContentFlow.collectAsStateWithLifecycle()

   Column(
      modifier = modifier
         .fillMaxSize()
         .padding(10.dp),
   ) {
      Text(
         text = annotated,
         fontSize = 16.sp,
         inlineContent = inlineContent,
      )
   }
}

private class Tag_user : AnnotatedHtml.Tag() {
   override fun elementText(element: Element, builder: AnnotatedString.Builder, text: String) {
      builder.appendInlineContent(id = "user")
      addInlineTextContent(
         id = "user",
         placeholderWidth = 200.sp,
         placeholderHeight = 24.sp,
      ) {
         Text(
            text = "$text world",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
         )
      }
   }
}
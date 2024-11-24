package com.sd.demo.compose.annotated.html

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose.annotated.html.theme.AppTheme
import com.sd.lib.compose.annotated.html.AnnotatedHtml

class SampleCommon : ComponentActivity() {

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
        <p>start(<b>bbbbb</b>)end</p>
        <p>start(<i>iiiii</i>)end</p>
        <p>start(<u>uuuuu</u>)end</p>
        <p>start(<strong>strong</strong>)end</p>
    """.trimIndent()

   val annotated = remember(html) { AnnotatedHtml().parse(html) }

   Column(
      modifier = modifier
         .fillMaxSize()
         .padding(10.dp),
   ) {
      Text(text = annotated)
   }
}
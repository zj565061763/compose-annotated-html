package com.sd.demo.compose.annotated.html

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.sd.demo.compose.annotated.html.theme.AppTheme
import com.sd.lib.compose.annotated.html.AnnotatedHtml

class Sample_p : ComponentActivity() {

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
      <p>before</p>
      <p style="text-align: start;">start</p>
      <p style="text-align: center;">center</p>
      <p style="text-align: end;">end</p>
      <p>after</p>
   """.trimIndent()

   val annotated = remember(html) { AnnotatedHtml().parse(html) }

   Column(
      modifier = modifier
         .fillMaxSize()
         .padding(10.dp),
   ) {
      Text(
         text = annotated,
         lineHeight = 1.em,
         modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(0.2f))
      )
   }
}
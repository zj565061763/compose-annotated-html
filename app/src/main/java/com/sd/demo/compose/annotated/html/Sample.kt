package com.sd.demo.compose.annotated.html

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose.annotated.html.theme.AppTheme

class Sample : ComponentActivity() {

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
   Column(
      modifier = modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState())
         .padding(10.dp),
   ) {
      AppTextView(html = htmlContent)
      HorizontalDivider()
      AndroidTextView(html = htmlContent)
   }
}

val htmlContent = """
<body>
    <h1>h1</h1>
    <h2>h2</h2>
    <h3>h3</h3>
    <h4>h4</h4>
    <h5>h5</h5>
    <h6>h6</h6>
    
    <p style="text-align: start;">start</p>
    <p style="text-align: center;">
      center
      <p style="text-align: start;">
         center start
      </p>
    </p>
    <p style="text-align: end;">end</p>
    
    <p>b:<b>bbb</b> i:<i>iii</i> u:<u>uuu</u> strong:<strong>strong</strong> em:<em>em</em></p>
    
    <p>test a <a href="https://www.baidu.com">click</a> here</p>
    
    <p>
      image:<img src="" alt="" />
    </p>
    
    <ul>
        <li>ul li 1</li>
        <li>ul li 2</li>
    </ul>
    
    <ol>
        <li>ol li 1</li>
        <li>ol li 2</li>
    </ol>
    
    <blockquote>This is a blockquote.</blockquote>
    
    <p>Here is a line break:</p>
    before br<br/>after br
</body>
"""
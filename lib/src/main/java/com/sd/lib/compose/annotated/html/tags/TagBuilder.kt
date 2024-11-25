package com.sd.lib.compose.annotated.html.tags

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.jsoup.nodes.Element

abstract class TagBuilder {
   private val _inlineTextContentFlow = MutableStateFlow<Map<String, InlineTextContent>>(emptyMap())

   val inlineTextContentFlow: Flow<Map<String, InlineTextContent>>
      get() = _inlineTextContentFlow.asStateFlow()

   protected fun addInlineTextContent(
      id: String,
      placeholderWidth: TextUnit = 1.em,
      placeholderHeight: TextUnit = 1.em,
      placeholderVerticalAlign: PlaceholderVerticalAlign = PlaceholderVerticalAlign.TextBottom,
      content: @Composable (String) -> Unit,
   ) {
      addInlineTextContent(
         id = id,
         placeholder = Placeholder(
            width = placeholderWidth,
            height = placeholderHeight,
            placeholderVerticalAlign = placeholderVerticalAlign,
         ),
         content = content,
      )
   }

   protected fun addInlineTextContent(
      id: String,
      placeholder: Placeholder,
      content: @Composable (String) -> Unit,
   ) {
      _inlineTextContentFlow.update {
         val inlineTextContent = InlineTextContent(
            placeholder = placeholder,
            children = content,
         )
         it + (id to inlineTextContent)
      }
   }

   open fun buildText(
      builder: AnnotatedString.Builder,
      text: String,
   ) {
      builder.append(text)
   }

   open fun beforeElement(
      element: Element,
      builder: AnnotatedString.Builder,
   ) = Unit

   open fun afterElement(
      element: Element,
      builder: AnnotatedString.Builder,
      start: Int, end: Int,
   ) = Unit
}
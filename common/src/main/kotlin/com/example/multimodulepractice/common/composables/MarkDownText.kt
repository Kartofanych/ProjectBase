package com.example.multimodulepractice.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.halilibo.richtext.commonmark.CommonmarkAstNodeParser
import com.halilibo.richtext.commonmark.MarkdownParseOptions
import com.halilibo.richtext.markdown.BasicMarkdown
import com.halilibo.richtext.ui.BasicRichText

@Composable
fun MarkDownText(modifier: Modifier, text: String) {
    val parser = remember { CommonmarkAstNodeParser(MarkdownParseOptions.Default) }
    val astNode = parser.parse(
        text.trimIndent()
    )
    BasicRichText(
        modifier = modifier
    ) {
        BasicMarkdown(astNode)
    }
}
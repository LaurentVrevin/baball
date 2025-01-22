package com.laurentvrevin.baball.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurentvrevin.baball.presentation.ui.theme.BlueText

@Composable
fun RestartButton(onRestart: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onRestart() }
            .padding(16.dp)
            .background(
                color = BlueText,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 12.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "One more ?",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
    }
}
@Preview
@Composable
fun RestartButtonPreview() {
    RestartButton(onRestart = {})
}
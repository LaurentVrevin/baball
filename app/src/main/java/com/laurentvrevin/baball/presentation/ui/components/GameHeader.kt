package com.laurentvrevin.baball.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameHeader(timer: Int, explosionCounter: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Explosions: $explosionCounter",
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            letterSpacing = 0.5.sp
        )
        Text(
            text = "Time left: $timer",
            color = Color.White,
            modifier = Modifier.padding(end = 16.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            letterSpacing = 0.5.sp
        )
    }
}
@Preview
@Composable
fun GameHeaderPreview() {
    GameHeader(timer = 30, explosionCounter = 5)
}
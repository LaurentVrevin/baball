package com.laurentvrevin.baball.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EndScreen(finalScore: Int, onRestart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Game Over!",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Final Score: $finalScore explosions",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Restart",
                color = Color.Blue,
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onRestart() }
                    .padding(16.dp)
            )
        }
    }
}
@Preview
@Composable
fun EndScreenPreview() {
    EndScreen(finalScore = 5, onRestart = {})

}
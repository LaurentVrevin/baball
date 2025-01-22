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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laurentvrevin.baball.presentation.ui.components.RestartButton
import com.laurentvrevin.baball.presentation.ui.theme.BlueText
import com.laurentvrevin.baball.presentation.ui.theme.gamingFont

@Composable
fun EndScreen(finalScore: Int, onRestart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "GG !!!",
                color = BlueText,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                lineHeight = 60.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "You explosed : $finalScore balls ",
                color = Color.Black,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,

                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            RestartButton(onRestart = onRestart)
        }
    }
}
@Preview
@Composable
fun EndScreenPreview() {
    EndScreen(finalScore = 5, onRestart = {})

}
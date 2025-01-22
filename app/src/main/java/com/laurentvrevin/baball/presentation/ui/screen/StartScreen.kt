package com.laurentvrevin.baball.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.laurentvrevin.baball.presentation.ui.theme.BlueText


@Composable
fun StartScreen(onStart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable { onStart() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Do you want to play ?",
                color = BlueText,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                lineHeight = 60.sp,
                modifier = Modifier
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Click the screen to start",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,

            )
        }
    }
}
@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(onStart = {})
}
package com.laurentvrevin.baball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.laurentvrevin.baball.presentation.ui.screen.OptimizedExplodingBallsScreen
import com.laurentvrevin.baball.presentation.ui.theme.BaballTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaballTheme {
                OptimizedExplodingBallsScreen(
                    )
                }
            }
        }

}

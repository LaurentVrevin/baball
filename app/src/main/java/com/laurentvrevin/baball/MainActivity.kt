package com.laurentvrevin.baball

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.laurentvrevin.baball.presentation.ui.screen.OptimizedExplodingBallsScreen
import com.laurentvrevin.baball.presentation.ui.theme.BaballTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.insetsController?.apply {
            hide(android.view.WindowInsets.Type.statusBars())
            hide(android.view.WindowInsets.Type.navigationBars())
            systemBarsBehavior = android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            BaballTheme {
                OptimizedExplodingBallsScreen(
                    )
                }
            }
        }

}

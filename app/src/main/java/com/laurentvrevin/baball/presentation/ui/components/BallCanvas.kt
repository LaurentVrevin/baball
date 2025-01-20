package com.laurentvrevin.baball.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.utils.graphics.drawBalls
import com.laurentvrevin.baball.utils.graphics.drawExplosions

@Composable
fun BallCanvas(
    balls: List<Ball>,
    explosions: List<Explosion>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawBalls(balls)
        drawExplosions(explosions)
    }
}
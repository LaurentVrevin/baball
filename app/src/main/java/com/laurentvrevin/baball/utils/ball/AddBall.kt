package com.laurentvrevin.baball.utils.ball

import androidx.compose.animation.core.Animatable
import androidx.compose.ui.graphics.Color
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Velocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun addBall(
    coroutineScope: CoroutineScope,
    balls: MutableList<Ball>,
    x: Float,
    y: Float
) {
    coroutineScope.launch {
        balls.add(
            Ball(
                id = balls.size,
                color = Color(
                    (0..255).random() / 255f,
                    (0..255).random() / 255f,
                    (0..255).random() / 255f
                ),
                positionY = Animatable(y),
                positionX = Animatable(x),
                velocity = Velocity(
                    x = (-200..200).random().toFloat(),
                    y = (-200..200).random().toFloat()
                ),
                size = (20..80).random().toFloat()
            )
        )
    }
}
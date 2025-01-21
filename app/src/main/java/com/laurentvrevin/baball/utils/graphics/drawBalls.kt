package com.laurentvrevin.baball.utils.graphics


import com.laurentvrevin.baball.domain.model.Ball
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawBalls(balls: List<Ball>) {
    balls.forEach { ball ->
        drawCircle(
            color = ball.color,
            radius = ball.size,
            center = Offset(ball.positionX.value, ball.positionY.value)
        )
    }
}
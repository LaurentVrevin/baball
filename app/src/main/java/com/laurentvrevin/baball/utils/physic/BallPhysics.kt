package com.laurentvrevin.baball.utils.physic

import com.laurentvrevin.baball.domain.model.Ball
import kotlin.math.absoluteValue

suspend fun updateBallPositionAndVelocity(
    ball: Ball,
    deltaTime: Float,
    gravity: Float,
    damping: Float,
    speedThreshold: Float,
    screenWidth: Float,
    screenHeight: Float
) {
    // Update speeds with gravity
    ball.velocity = ball.velocity.copy(
        y = ball.velocity.y + gravity * deltaTime
    )

    // Calculation of new positions
    val nextX = ball.positionX.value + ball.velocity.x * deltaTime
    val nextY = ball.positionY.value + ball.velocity.y * deltaTime

    // Management of bounces on walls
    if (nextX - ball.size <= 0 || nextX + ball.size >= screenWidth) {
        ball.velocity = ball.velocity.copy(
            x = -ball.velocity.x * damping
        )
        ball.positionX.snapTo(nextX.coerceIn(ball.size, screenWidth - ball.size))
    } else {
        ball.positionX.snapTo(nextX)
    }

    // Ground bounce management
    if (nextY + ball.size >= screenHeight) {
        ball.velocity = ball.velocity.copy(
            y = -ball.velocity.y * damping
        )
        ball.positionY.snapTo(screenHeight - ball.size)
    } else if ((ball.positionY.value - nextY).absoluteValue > 0.01f) {
        ball.positionY.snapTo(nextY)
    }

    // Speed reduction when close to 0
    if (ball.velocity.x.absoluteValue < speedThreshold) {
        ball.velocity = ball.velocity.copy(x = 0f)
    }
    if (ball.velocity.y.absoluteValue < speedThreshold) {
        ball.velocity = ball.velocity.copy(y = 0f)
    }
}

// Builds a spatial grid to optimize collision detection
fun buildSpatialGrid(
    balls: List<Ball>,
    gridSize: Float
): Map<Pair<Int, Int>, MutableList<Ball>> {
    val spatialGrid = mutableMapOf<Pair<Int, Int>, MutableList<Ball>>()
    balls.filter { it.isActive }.forEach { ball ->
        // Using current position values
        val cellX = (ball.positionX.value / gridSize).toInt()
        val cellY = (ball.positionY.value / gridSize).toInt()
        spatialGrid.getOrPut(cellX to cellY) { mutableListOf() }.add(ball)
    }
    return spatialGrid
}

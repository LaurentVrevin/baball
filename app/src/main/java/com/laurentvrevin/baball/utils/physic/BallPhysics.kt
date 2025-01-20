package com.laurentvrevin.baball.utils.physic


import com.laurentvrevin.baball.domain.model.Ball
import kotlin.math.absoluteValue

// Updates ball's position and velocity based on physics
suspend fun updateBallPositionAndVelocity(
    ball: Ball,
    deltaTime: Float,
    gravity: Float,
    damping: Float,
    speedThreshold: Float,
    screenWidth: Float,
    screenHeight: Float
) {
    if (ball.velocityX.absoluteValue < speedThreshold && ball.velocityY.absoluteValue < speedThreshold) return

    ball.velocityY += gravity * deltaTime
    ball.positionX += ball.velocityX * deltaTime
    val nextY = ball.positionY.value + ball.velocityY * deltaTime

    if (ball.positionX - ball.size <= 0 || ball.positionX + ball.size >= screenWidth) {
        ball.velocityX = -ball.velocityX * damping
        ball.positionX = ball.positionX.coerceIn(ball.size, screenWidth - ball.size)
    }

    if (nextY + ball.size >= screenHeight) {
        ball.velocityY = -ball.velocityY * damping
        ball.positionY.snapTo(screenHeight - ball.size)
    } else if ((ball.positionY.value - nextY).absoluteValue > 0.01f) {
        ball.positionY.snapTo(nextY)
    }
}

// Builds a spatial grid to optimize collision detection
fun buildSpatialGrid(
    balls: List<Ball>,
    gridSize: Float
): Map<Pair<Int, Int>, MutableList<Ball>> {
    val spatialGrid = mutableMapOf<Pair<Int, Int>, MutableList<Ball>>()
    balls.forEach { ball ->
        val cellX = (ball.positionX / gridSize).toInt()
        val cellY = (ball.positionY.value / gridSize).toInt()
        spatialGrid.getOrPut(cellX to cellY) { mutableListOf() }.add(ball)
    }
    return spatialGrid
}
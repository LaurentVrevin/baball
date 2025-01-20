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
    // Calculate speed and position only if ball's moving
    ball.velocityY += gravity * deltaTime
    ball.positionX += ball.velocityX * deltaTime
    val nextY = ball.positionY.value + ball.velocityY * deltaTime

    // Rebound on the wall
    if (ball.positionX - ball.size <= 0 || ball.positionX + ball.size >= screenWidth) {
        ball.velocityX = -ball.velocityX * damping
        ball.positionX = ball.positionX.coerceIn(ball.size, screenWidth - ball.size)
    }

    // Rebound on the ground
    if (nextY + ball.size >= screenHeight) {
        ball.velocityY = -ball.velocityY * damping
        ball.positionY.snapTo(screenHeight - ball.size)
    } else if ((ball.positionY.value - nextY).absoluteValue > 0.01f) {
        ball.positionY.snapTo(nextY)
    }

    // Reduce speed near 0
    if (ball.velocityX.absoluteValue < speedThreshold) ball.velocityX = 0f
    if (ball.velocityY.absoluteValue < speedThreshold) ball.velocityY = 0f
}

// Builds a spatial grid to optimize collision detection
fun buildSpatialGrid(
    balls: List<Ball>,
    gridSize: Float
): Map<Pair<Int, Int>, MutableList<Ball>> {
    val spatialGrid = mutableMapOf<Pair<Int, Int>, MutableList<Ball>>()
    balls.filter { it.isActive }.forEach { ball -> // Filter active balls
        val cellX = (ball.positionX / gridSize).toInt()
        val cellY = (ball.positionY.value / gridSize).toInt()
        spatialGrid.getOrPut(cellX to cellY) { mutableListOf() }.add(ball)
    }
    return spatialGrid
}
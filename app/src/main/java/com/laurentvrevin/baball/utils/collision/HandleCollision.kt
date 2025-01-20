package com.laurentvrevin.baball.utils.collision

import com.laurentvrevin.baball.domain.model.Ball
import kotlin.math.sqrt

suspend fun handleCollision(ball1: Ball, ball2: Ball): Boolean {
    val dx = ball1.positionX - ball2.positionX
    val dy = ball1.positionY.value - ball2.positionY.value
    val distance = sqrt(dx * dx + dy * dy)
    val combinedSize = ball1.size + ball2.size

    if (distance < combinedSize) {
        val nx = dx / distance
        val ny = dy / distance

        val relativeVelocityX = ball1.velocityX - ball2.velocityX
        val relativeVelocityY = ball1.velocityY - ball2.velocityY
        val dotProduct = relativeVelocityX * nx + relativeVelocityY * ny

        if (dotProduct < 0) {
            val impulse = 2 * dotProduct / 2
            ball1.velocityX -= impulse * nx
            ball1.velocityY -= impulse * ny
            ball2.velocityX += impulse * nx
            ball2.velocityY += impulse * ny
        }

        val overlap = combinedSize - distance
        ball1.positionX += nx * overlap / 2
        ball1.positionY.snapTo(ball1.positionY.value + ny * overlap / 2)
        ball2.positionX -= nx * overlap / 2
        ball2.positionY.snapTo(ball2.positionY.value - ny * overlap / 2)

        return true
    }

    return false
}
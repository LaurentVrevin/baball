package com.laurentvrevin.baball.utils.collision

import com.laurentvrevin.baball.domain.model.Ball

suspend fun handleCollision(ball1: Ball, ball2: Ball): Boolean {
    // Extract current positions
    val dx = ball1.positionX.value - ball2.positionX.value
    val dy = ball1.positionY.value - ball2.positionY.value
    val distance = kotlin.math.sqrt(dx * dx + dy * dy)
    val combinedSize = ball1.size + ball2.size

    if (distance < combinedSize) {
        val nx = dx / distance
        val ny = dy / distance

        // Calculation of relative speeds
        val relativeVelocityX = ball1.velocity.x - ball2.velocity.x
        val relativeVelocityY = ball1.velocity.y - ball2.velocity.y
        val dotProduct = relativeVelocityX * nx + relativeVelocityY * ny

        if (dotProduct < 0) {
            val impulse = 2 * dotProduct / 2
            ball1.velocity = ball1.velocity.copy(
                x = ball1.velocity.x - impulse * nx,
                y = ball1.velocity.y - impulse * ny
            )
            ball2.velocity = ball2.velocity.copy(
                x = ball2.velocity.x + impulse * nx,
                y = ball2.velocity.y + impulse * ny
            )
        }

        // Resolution of interpenetration
        val overlap = combinedSize - distance
        ball1.positionX.snapTo(ball1.positionX.value + nx * overlap / 2)
        ball1.positionY.snapTo(ball1.positionY.value + ny * overlap / 2)
        ball2.positionX.snapTo(ball2.positionX.value - nx * overlap / 2)
        ball2.positionY.snapTo(ball2.positionY.value - ny * overlap / 2)

        return true
    }

    return false
}
package com.laurentvrevin.baball.utils.explosion

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

// Handles explosions for balls exceeding the collision threshold
fun processExplosions(
    balls: MutableList<Ball>,
    explosions: MutableList<Explosion>,
    explosionCounter: Int,
    collisionThreshold: Int,
    coroutineScope: CoroutineScope
) {
    val explodedBalls = balls.filter { it.collisionCount >= collisionThreshold }
    explodedBalls.forEach { ball ->
        coroutineScope.launch {
            val explosion = Explosion(
                id = explosionCounter.absoluteValue,
                positionX = ball.positionX,
                positionY = ball.positionY.value,
                radius = Animatable(0f),
                opacity = Animatable(1f)
            )
            explosions.add(explosion)

            launch {
                explosion.radius.animateTo(
                    targetValue = 150f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
            launch {
                explosion.opacity.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
            // Removes explosion after animation
            launch {
                kotlinx.coroutines.delay(800)
                explosions.remove(explosion)
            }
        }
    }
    balls.removeAll(explodedBalls)
}
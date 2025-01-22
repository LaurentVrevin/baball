package com.laurentvrevin.baball.utils.explosion

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.domain.model.ExplosionAnimationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

// Handles explosions for balls exceeding the collision threshold
fun processExplosions(
    balls: MutableList<Ball>,
    explosions: MutableList<Explosion>,
    explosionCounter: Int,
    collisionThreshold: Int,
    coroutineScope: CoroutineScope,
    config: ExplosionAnimationConfig, // Configurations for explosions
    onExplosion: () -> Unit // Callback to notify an explosion
) {
    val explodedBalls = balls.filter { it.collisionCount >= collisionThreshold }
    explodedBalls.forEach { ball ->
        coroutineScope.launch {
            val explosion = Explosion(
                id = explosionCounter.absoluteValue,
                position = Offset(ball.positionX.value, ball.positionY.value), // Using Offset
                radius = Animatable(0f),
                opacity = Animatable(1f)
            )
            explosions.add(explosion)
            onExplosion() // Notify that an explosion has occurred

            // Animation for explosion radius
            launch {
                explosion.radius.animateTo(
                    targetValue = config.explosionRadius,
                    animationSpec = tween(durationMillis = config.radiusDuration)
                )
            }
            // Animation for opacity
            launch {
                explosion.opacity.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = config.opacityDuration)
                )
            }
            // Remove the explosion after the animation
            launch {
                kotlinx.coroutines.delay(config.radiusDuration.toLong() + 300) // Safety buffer
                explosions.remove(explosion)
            }
        }
    }
    balls.removeAll(explodedBalls)
}
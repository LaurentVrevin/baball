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
    config: ExplosionAnimationConfig // Configurations pour les explosions
) {
    val explodedBalls = balls.filter { it.collisionCount >= collisionThreshold }
    explodedBalls.forEach { ball ->
        coroutineScope.launch {
            val explosion = Explosion(
                id = explosionCounter.absoluteValue,
                position = Offset(ball.positionX.value, ball.positionY.value), // Utilisation d'Offset
                radius = Animatable(0f),
                opacity = Animatable(1f)
            )
            explosions.add(explosion)

            // Animation du rayon de l'explosion
            launch {
                explosion.radius.animateTo(
                    targetValue = config.explosionRadius,
                    animationSpec = tween(durationMillis = config.radiusDuration)
                )
            }
            // Animation de l'opacité
            launch {
                explosion.opacity.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = config.opacityDuration)
                )
            }
            // Suppression de l'explosion après l'animation
            launch {
                kotlinx.coroutines.delay(config.radiusDuration.toLong() + 300) // Temps de sécurité
                explosions.remove(explosion)
            }
        }
    }
    balls.removeAll(explodedBalls)
}
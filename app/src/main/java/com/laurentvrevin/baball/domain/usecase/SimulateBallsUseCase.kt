package com.laurentvrevin.baball.domain.usecase

import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.utils.collision.processCollisions
import com.laurentvrevin.baball.utils.explosion.processExplosions
import com.laurentvrevin.baball.utils.physic.buildSpatialGrid
import com.laurentvrevin.baball.utils.physic.updateBallPositionAndVelocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SimulateBallsUseCase(
    private val coroutineScope: CoroutineScope
) {
    fun execute(
        balls: MutableList<Ball>,
        explosions: MutableList<Explosion>,
        gravity: Float,
        damping: Float,
        collisionThreshold: Int,
        speedThreshold: Float,
        screenWidth: Float,
        screenHeight: Float,
        gridSize: Float,
        explosionCounter: Int
    ) {
        coroutineScope.launch {
            while (true) {
                val deltaTime = 0.016f

                // Update ball positions and velocities
                balls.forEach { ball ->
                    updateBallPositionAndVelocity(
                        ball,
                        deltaTime,
                        gravity,
                        damping,
                        speedThreshold,
                        screenWidth,
                        screenHeight
                    )
                }

                // Detect collisions
                val spatialGrid = buildSpatialGrid(balls, gridSize)
                processCollisions(balls, spatialGrid, gridSize)

                // Manage explosions
                processExplosions(balls, explosions, explosionCounter, collisionThreshold, coroutineScope)

                // Wait for the next frame
                kotlinx.coroutines.delay(16L)
            }
        }
    }
}
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

                // Create spatial grid for collision optimization
                val spatialGrid = buildSpatialGrid(balls, gridSize)

                // Update each ball's position and velocity
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
                // Process collisions and explosions
                processCollisions(balls, spatialGrid, gridSize)
                processExplosions(balls, explosions, explosionCounter, collisionThreshold, coroutineScope)

                kotlinx.coroutines.delay(16L)
            }
        }
    }
}
package com.laurentvrevin.baball.domain.usecase

import android.util.Log
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.domain.model.ExplosionAnimationConfig
import com.laurentvrevin.baball.utils.collision.processCollisions
import com.laurentvrevin.baball.utils.explosion.processExplosions
import com.laurentvrevin.baball.utils.physic.buildSpatialGrid
import com.laurentvrevin.baball.utils.physic.updateBallPositionAndVelocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SimulateBallsUseCase(
    private val coroutineScope: CoroutineScope,
    private val explosionConfig: ExplosionAnimationConfig = ExplosionAnimationConfig()
) {
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> get() = _isRunning

    fun start(
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
        if (_isRunning.value) return

        _isRunning.value = true

        coroutineScope.launch {
            while (_isRunning.value) {
                simulateFrame(
                    balls,
                    explosions,
                    gravity,
                    damping,
                    collisionThreshold,
                    speedThreshold,
                    screenWidth,
                    screenHeight,
                    gridSize,
                    explosionCounter
                )
                kotlinx.coroutines.delay(16L)
            }
        }
    }

    private suspend fun simulateFrame(
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
        try {
            val deltaTime = 0.016f
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
            val spatialGrid = buildSpatialGrid(balls, gridSize)
            processCollisions(balls, spatialGrid, gridSize)
            processExplosions(
                balls,
                explosions,
                explosionCounter,
                collisionThreshold,
                coroutineScope,
                config = explosionConfig
            )
        } catch (e: Exception) {
            Log.e("SimulateBallsUseCase", "Error during simulation frame", e)
        }
    }

    fun stop() {
        if (!_isRunning.value) return
        _isRunning.value = false

    }
}
package com.laurentvrevin.baball.utils.collision

import com.laurentvrevin.baball.domain.model.Ball

// Processes collisions between balls in neighboring grid cells
suspend fun processCollisions(
    balls: List<Ball>,
    spatialGrid: Map<Pair<Int, Int>, MutableList<Ball>>,
    gridSize: Float
) {
    balls.forEach { ball ->
        // Using the current values of positionX and positionY
        val cellX = (ball.positionX.value / gridSize).toInt()
        val cellY = (ball.positionY.value / gridSize).toInt()

        for (neighborCellX in (cellX - 1)..(cellX + 1)) {
            for (neighborCellY in (cellY - 1)..(cellY + 1)) {
                val neighbors = spatialGrid[neighborCellX to neighborCellY] ?: continue
                neighbors.forEach { neighbor ->
                    if (ball != neighbor) {
                        if (handleCollision(ball, neighbor)) {
                            ball.collisionCount++
                            neighbor.collisionCount++
                        }
                    }
                }
            }
        }
    }
}
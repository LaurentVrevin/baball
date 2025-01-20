package com.laurentvrevin.baball.domain.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.graphics.Color

data class Ball(
    val id: Int,
    val color: Color,
    val positionY: Animatable<Float, AnimationVector1D>,
    var positionX: Float,
    var velocityX: Float,
    var velocityY: Float,
    var size: Float = 50f,
    var collisionCount: Int = 0,
    var isActive: Boolean = true,
    var shouldBeRemoved: Boolean = false
)

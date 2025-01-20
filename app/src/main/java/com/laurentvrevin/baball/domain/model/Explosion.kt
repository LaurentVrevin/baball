package com.laurentvrevin.baball.domain.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D

data class Explosion(
    val id: Int,
    val positionX: Float,
    val positionY: Float,
    val radius: Animatable<Float, AnimationVector1D>,
    val opacity: Animatable<Float, AnimationVector1D>,
)

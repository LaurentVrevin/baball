package com.laurentvrevin.baball.domain.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.Offset

data class Explosion(
    val id: Int,
    val position: Offset,
    val radius: Animatable<Float, AnimationVector1D>,
    val opacity: Animatable<Float, AnimationVector1D>,
)

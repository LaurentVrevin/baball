package com.laurentvrevin.baball.utils.graphics

import com.laurentvrevin.baball.domain.model.Explosion
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawExplosions(explosions: List<Explosion>) {
    explosions.forEach { explosion ->
        drawCircle(
            color = Color.Red.copy(alpha = explosion.opacity.value),
            radius = explosion.radius.value,
            center = Offset(explosion.position.x, explosion.position.y)
        )
    }
}
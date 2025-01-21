package com.laurentvrevin.baball.presentation.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.domain.model.ExplosionAnimationConfig
import com.laurentvrevin.baball.domain.model.Velocity
import com.laurentvrevin.baball.domain.usecase.SimulateBallsUseCase
import com.laurentvrevin.baball.presentation.ui.components.BallCanvas
import kotlinx.coroutines.launch

@Composable
fun OptimizedExplodingBallsScreen() {
    val coroutineScope = rememberCoroutineScope()

    val simulateBallsUseCase = remember {
        SimulateBallsUseCase(coroutineScope)
    }
    val gravity = 900f
    val damping = 0.7f
    val collisionThreshold = 20
    val speedThreshold = 0.001f

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp * LocalDensity.current.density
    val screenWidth = configuration.screenWidthDp * LocalDensity.current.density
    val gridSize = 100f

    val balls = remember { mutableStateListOf<Ball>() }
    val explosions = remember { mutableStateListOf<Explosion>() }
    var ballCounter by remember { mutableIntStateOf(0) }
    var explosionCounter by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    coroutineScope.launch {
                        balls.add(
                            Ball(
                                id = ballCounter++,
                                color = Color(
                                    (0..255).random() / 255f,
                                    (0..255).random() / 255f,
                                    (0..255).random() / 255f
                                ),
                                positionY = Animatable(offset.y),
                                positionX = Animatable(offset.x),
                                velocity = Velocity(
                                    y = (-200..200).random().toFloat(),
                                    x = 0f
                                ),
                                size = (20..80).random().toFloat()
                            )
                        )
                    }
                }
            }
    ) {

        // Canvas to render balls and explosions
        BallCanvas(
            balls = balls,
            explosions = explosions,
            modifier = Modifier.fillMaxSize()
        )

        // Start simulation
        LaunchedEffect(Unit) {
            simulateBallsUseCase.start(
                balls = balls,
                explosions = explosions,
                gravity = gravity,
                damping = damping,
                collisionThreshold = collisionThreshold,
                speedThreshold = speedThreshold,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                gridSize = gridSize,
                explosionCounter = explosionCounter
            )
        }
        DisposableEffect(Unit) {
            onDispose {
                simulateBallsUseCase.stop()
            }
        }
    }
}
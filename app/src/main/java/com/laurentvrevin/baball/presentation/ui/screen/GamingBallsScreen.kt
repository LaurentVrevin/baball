package com.laurentvrevin.baball.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.domain.usecase.SimulateBallsUseCase
import com.laurentvrevin.baball.presentation.ui.components.BallCanvas
import com.laurentvrevin.baball.presentation.ui.components.GameHeader
import com.laurentvrevin.baball.utils.ball.addBall

@Composable
fun OptimizedExplodingBallsScreen() {

    val coroutineScope = rememberCoroutineScope()
    val simulateBallsUseCase = remember {
        SimulateBallsUseCase(coroutineScope)
    }

    val timerDefined = 20
    var isGameStarted by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(timerDefined) }
    var explosionCounter by remember { mutableIntStateOf(0) }
    val balls = remember { mutableStateListOf<Ball>() }
    val explosions = remember { mutableStateListOf<Explosion>() }

    // Manage Timer
    LaunchedEffect(isGameStarted, timer) {
        if (isGameStarted && timer > 0) {
            kotlinx.coroutines.delay(1000L)
            timer--
        } else if (timer == 0) {
            simulateBallsUseCase.stop()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(MaterialTheme.colorScheme.background, Color.White)
        )
    )
    ) {

    Column(modifier = Modifier.fillMaxSize()) {
        // Header: Timer and explosion counter
        GameHeader(timer = timer, explosionCounter = explosionCounter)

        // Simulation zone
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(isGameStarted) {
                    detectTapGestures { offset ->
                        if (isGameStarted && timer > 0) {
                            addBall(
                                coroutineScope = coroutineScope,
                                balls = balls,
                                x = offset.x,
                                y = offset.y
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

            // Start Screen
            if (!isGameStarted) {
                StartScreen {
                    isGameStarted = true
                    addBall(
                        coroutineScope = coroutineScope,
                        balls = balls,
                        x = 500f, // Initial position (example)
                        y = 800f
                    )
                }
            }

            // End Screen
            if (timer == 0) {
                EndScreen(
                    finalScore = explosionCounter,
                    onRestart = {
                        timer = timerDefined
                        explosionCounter = 0
                        balls.clear()
                        explosions.clear()
                        isGameStarted = false
                    }
                )
            }
        }
    }

    // Launch simulation
    if (isGameStarted && timer > 0) {
        LaunchedEffect(Unit) {
            simulateBallsUseCase.start(
                balls = balls,
                explosions = explosions,
                gravity = 900f,
                damping = 0.7f,
                collisionThreshold = 20,
                speedThreshold = 0.001f,
                screenWidth = 1080f,
                screenHeight = 1920f,
                gridSize = 100f,
                explosionCounter = explosionCounter,
                onExplosion = { explosionCounter++ }
            )
        }
    }

    // Clean up simulation
    DisposableEffect(Unit) {
        onDispose {
            simulateBallsUseCase.stop()
        }
    }
}
    }
@Preview
@Composable
fun OptimizedExplodingBallsScreenPreview() {
    OptimizedExplodingBallsScreen()
}
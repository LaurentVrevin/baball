package com.laurentvrevin.baball.presentation.ui.screen
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.laurentvrevin.baball.domain.model.Ball
import com.laurentvrevin.baball.domain.model.Explosion
import com.laurentvrevin.baball.domain.usecase.SimulateBallsUseCase
import com.laurentvrevin.baball.presentation.ui.components.BallCanvas
import kotlinx.coroutines.launch

@Composable
fun OptimizedExplodingBallsScreen() {
    val coroutineScope = rememberCoroutineScope()
    val simulateBallsUseCase = SimulateBallsUseCase(coroutineScope)
    val gravity = 900f
    val damping = 0.7f
    val collisionThreshold = 20
    val speedThreshold = 0.1f

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp * LocalDensity.current.density
    val screenWidth = configuration.screenWidthDp * LocalDensity.current.density
    val gridSize = 100f

    val balls = remember { mutableStateListOf<Ball>() }
    val explosions = remember { mutableStateListOf<Explosion>() }
    var ballCounter by remember { mutableIntStateOf(0) }
    var explosionCounter by remember { mutableIntStateOf(0) }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        // Button to add new balls
        Button(
            onClick = {
                coroutineScope.launch {
                    balls.add(
                        Ball(
                            id = ballCounter++,
                            color = Color(
                                (0..255).random() / 255f,
                                (0..255).random() / 255f,
                                (0..255).random() / 255f
                            ),
                            positionY = Animatable(0f),
                            positionX = (50..(screenWidth - 50).toInt()).random().toFloat(),
                            velocityX = (-200..200).random().toFloat(),
                            velocityY = 0f,
                            size = (20..80).random().toFloat()
                        )
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            Text("Add a Ball")
        }

        // Canvas to render balls and explosions
        BallCanvas(
            balls = balls,
            explosions = explosions,
            modifier = Modifier.fillMaxSize()
        )

        // Start simulation
        LaunchedEffect(Unit) {
            simulateBallsUseCase.execute(
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
    }
}
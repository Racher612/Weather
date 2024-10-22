package com.project.weather.common.compose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.weather.R
import com.project.weather.common.design.MediumText
import com.project.weather.common.design.gradientBackground

@Composable
fun SwipeHintScreen(modifier: Modifier = Modifier) {
    
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        MediumText(text = stringResource(id = R.string.hint))
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 24.dp)
                .background(colorResource(id = R.color.transparent))
        ) {
            val height = size.height
            val cometPath = Path().apply {
                addOval(androidx.compose.ui.geometry.Rect(
                    left = offsetX - 55,
                    top = height / 6,
                    right = offsetX + 35,
                    bottom = 3 * height / 6
                ))

                moveTo(offsetX, height / 2)
                lineTo(offsetX - 30, height / 6)
                lineTo(offsetX - 300, height / 3)
                lineTo(offsetX - 30, 3 * height / 6)
                close()
            }

            drawPath(
                path = cometPath,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Gray.copy(alpha = alpha),
                        Color.Gray.copy(alpha = 0.3f)
                    )
                )
            )
        }
    }

}
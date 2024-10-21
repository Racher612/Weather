package com.project.weather.common.design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.project.weather.R

@Composable
fun Modifier.gradientBackground(
    shape: Shape = RectangleShape,
) = background(
    brush = Brush.horizontalGradient(
    colors = listOf(
        colorResource(id = R.color.purple),
        colorResource(id = R.color.black)
    ),
        startX = 10.0f,
        endX = 1500.0f
    ),
    shape = shape,
)
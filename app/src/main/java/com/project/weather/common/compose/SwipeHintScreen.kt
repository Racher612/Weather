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
import androidx.compose.ui.unit.dp
import com.project.weather.common.design.MediumText
import com.project.weather.common.design.gradientBackground

@Composable
fun SwipeHintScreen(modifier: Modifier = Modifier) {
    
    val infiniteTransition = rememberInfiniteTransition()

    // Анимация смещения "кометы"
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -200f,  // Начальная позиция за экраном слева
        targetValue = 800f,    // Конечная позиция за экраном справа
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing), // Длительность 1.5 сек
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Анимация изменения прозрачности
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,   // Полупрозрачный старт
        targetValue = 0.1f,    // Прозрачность уменьшается к хвосту
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
        MediumText(text = "There is no location in your list.\nAdd some to view weather")
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp) // Уменьшенная высота
                .padding(horizontal = 24.dp) // Равные отступы по краям
                .background(Color.Transparent) // Убираем фон
        ) {
            val height = size.height

//            Log.d("HEIGHT", offsetX.toString())

            // Рисуем путь для "кометы" с хвостом
            val cometPath = Path().apply {
                // Эллипсоидная головка кометы
                addOval(androidx.compose.ui.geometry.Rect(
                    left = offsetX - 35,  // Левый край эллипса
                    top = height / 6,     // Верхний край
                    right = offsetX + 35, // Правый край
                    bottom = 3 * height / 6 // Нижний край
                ))

                // Хвост кометы
                moveTo(offsetX, height / 2)               // Начальная точка хвоста
                lineTo(offsetX - 30, height / 6)          // Толстая передняя часть хвоста
                lineTo(offsetX - 300, height / 3)         // Сужающийся участок хвоста
                lineTo(offsetX - 80, 3 * height / 6)      // Низ хвоста
                close()                                   // Замыкаем путь
            }

            drawPath(
                path = cometPath,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Gray.copy(alpha = alpha),      // Голова кометы
                        Color.Gray.copy(alpha = 0.3f)        // Хвост кометы
                    )
                )
            )

        }
    }

}
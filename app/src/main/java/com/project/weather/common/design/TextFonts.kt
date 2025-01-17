package com.project.weather.common.design

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.project.weather.R

@Composable
fun MediumText(
    text : String,
    color : Color = colorResource(id = R.color.white)
    ){
    Text(
        text = text,
        color = color,
        fontSize = 18.sp
    )
}
package com.project.weather.common.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.weather.R

@Composable
fun PlusButton(
    onClick: () -> Unit,
){
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            colorResource(id = R.color.white),
            colorResource(id = R.color.cyan_fae),
            colorResource(id = R.color.white),
            colorResource(id = R.color.cyan_fae)
        ),
        shape = CircleShape,
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.plus),
            contentDescription = null,
        )
    }
}
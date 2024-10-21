package com.project.weather.common.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.weather.R
import com.project.weather.common.design.gradientBackground

@Composable
fun CityIcon(
    text : String,
    pinOperation: () -> Unit,
    deleteOperation: () -> Unit,
    pinned: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable{
                onClick()
            }
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.white),
                    RoundedCornerShape(8.dp)
                )

                .gradientBackground(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = text,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .weight(4f)
            )
            Icon(
                painter = painterResource(id = if (pinned) R.drawable.filled_pin else R.drawable.pin),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        pinOperation()
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.trashbox),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier
                    .weight(1f)
                    .size(25.dp)
                    .clickable {
                        deleteOperation()
                    }
            )
        }
    }

}
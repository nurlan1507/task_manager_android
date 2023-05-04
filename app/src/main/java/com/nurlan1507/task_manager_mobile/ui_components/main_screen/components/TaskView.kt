package com.nurlan1507.task_manager_mobile.ui_components.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskView(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ) {

        }){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){
            Box(
                modifier = Modifier.size(20.dp).offset(y=4.dp)
                    .background(Color(0xFF81ACFA), shape = CircleShape)
                    .border(width = 2.dp, color = Color(0xFF5E97FF), shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column() {
                Text(text = "Проанализировать дент и составить план", style = MaterialTheme.typography.subtitle1)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "15 мая",
                        style = MaterialTheme.typography.body2.copy(color = Color(0xFF5E97FF))
                    )
                    Text(
                        text = "Работа",
                        style = MaterialTheme.typography.body2.copy(color = Color.Gray)
                    )
                }
            }
        }
    }
}
package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TaskCreationButton(icon:ImageVector, text:String ){
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .border(width = 3.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
        .clickable {
        }
    ){
        Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)){
            Icon(icon, tint = Color.Gray, contentDescription = "Срок выполнения")
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = text, style = MaterialTheme.typography.body2, color = Color.Gray)
        }
    }
}
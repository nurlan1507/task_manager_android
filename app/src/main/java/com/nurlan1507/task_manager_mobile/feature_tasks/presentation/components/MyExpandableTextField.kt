package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MyExpandableTextField(value:String, onValueChange:(String)->Unit){
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value =value,
        onValueChange = {onValueChange(it)},
        placeholder = {Text("Покормить кота", style = MaterialTheme.typography.body1 , fontWeight = FontWeight.Light)},
        textStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
    )
}
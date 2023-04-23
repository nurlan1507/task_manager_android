package com.nurlan1507.task_manager_mobile.global_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> TopBar(
    title: String,
    actionData: List<T> = listOf(),
    navigationIcon: @Composable() (() -> Unit)? = null,
    action: @Composable() (T) -> Unit
    // TODO: support overflow menu here with the remainder of the list
){
    TopAppBar(
        title ={Text(text = title, color = Color.Black, fontWeight = FontWeight.SemiBold)} ,
        modifier = Modifier
            .height(55.dp)
            .padding(horizontal = 5.dp,vertical = 10.dp)
            .background(Color(0xFF5E97FF)),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.MoreVert,"more")
            }
        },
    )
}
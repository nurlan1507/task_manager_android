package com.nurlan1507.task_manager_mobile.global_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> BottomNavigationBar(
    navigationIcon: @Composable() (() -> Unit)? = null,
    actionData: List<T> = emptyList(),
    action: @Composable RowScope.() -> Unit = {}
){
    val bottomBarHeight = 70.dp
//    BottomAppBar(
//        containerColor = Color(0xFF5E97FF),
//        contentColor = Color.White,
//        actions = {
//            IconButton(onClick = { /* doSomething() */ }) {
//                Icon(Icons.Filled.List, contentDescription = "Drawer")
//            }
//            IconButton(onClick = { /* doSomething() */ }) {
//                Icon(
//                    Icons.Filled.Notifications,
//                    contentDescription = "Notifications",
//                )
//            }
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                modifier = Modifier.offset(y=(-19).dp),
//                onClick = { /* do something */ },
//                shape = CircleShape,
//                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
//                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
//            ) {
//                Icon(Icons.Filled.Add, "Add a task")
//            }
//        }
//    )
    Box (modifier = Modifier.wrapContentSize()){
        BottomAppBar(
            modifier = Modifier.height(bottomBarHeight).fillMaxWidth(),
            containerColor = Color(0xFF5E97FF),
            contentColor = Color.White,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Handle Home click */ }) {
                    Icon(Icons.Default.List, contentDescription = "Home")
                }
                Row(){
                    IconButton(onClick = { /* Handle Settings click */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /* Handle Settings click */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Settings")
                    }
                }

            }
        }

        FloatingActionButton(
            shape = CircleShape,
            containerColor = Color(0xFF5E97FF),
            onClick = { /* Handle FloatingActionButton click */ },
            modifier = Modifier.align(Alignment.BottomCenter).size(70.dp).offset(y = -(bottomBarHeight/2)).border(width = 6.dp, color = Color.Transparent, shape = RoundedCornerShape(bottomBarHeight/2))
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White )
        }
    }
}
//    Box(modifier = Modifier
//        .height(60.dp)
//        .background(Color(0xFF5E97FF))){
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 4.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Box(modifier = Modifier.fillMaxWidth().wrapContentHeight().offset(y=(-).dp), contentAlignment = Alignment.Center) {
//                FloatingActionButton(onClick = { /*TODO*/ },  modifier = Modifier
//                    .size(65.dp)
//                    .border(width = 5.dp, shape = CircleShape, color = Color.White),
//                    shape = CircleShape,
//                    containerColor = Color(0xFF5E97FF),
//                    ) {
//                    Icon(Icons.Default.Add,"add a task", tint = Color.White)
//                }
//            }
//        }
//    }

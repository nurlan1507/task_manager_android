package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.global_components.BottomNavigationBar
import com.nurlan1507.task_manager_mobile.global_components.TopBar
import com.nurlan1507.task_manager_mobile.utils.WindowSize

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController,windowSize: WindowSize){
    var isBottomSheetVisible by remember{ mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar<String>(title = "Сегодня"){

            }
        },
        content = {
                  Column() {

                  }
        },
        bottomBar = {
            BottomNavigationBar<String>()
        }
    )
}   
package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.BottomSheetLayoutState
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.BottomSheet
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.BottomSheetContent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts.MainBottomSheetLayout
import com.nurlan1507.task_manager_mobile.global_components.BottomNavigationBar
import com.nurlan1507.task_manager_mobile.global_components.TopBar
import com.nurlan1507.task_manager_mobile.utils.WindowSize
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "CoroutineCreationDuringComposition"
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController,windowSize: WindowSize){
    var currentBottomSheetLayout by remember{mutableStateOf(CurrentBottomSheetLayout(null))}
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if(it == ModalBottomSheetValue.Hidden){
                currentBottomSheetLayout = currentBottomSheetLayout.copy(type = null)
            }
            true
        }
    )
    val scope = rememberCoroutineScope()
    var bottomSheetState by remember{mutableStateOf(BottomSheetLayoutState())}


    when(currentBottomSheetLayout.type){
        BottomSheetLayoutType.NOTIFICATIONS ->{
            bottomSheetState = bottomSheetState.copy(layout = {BottomSheetContent()})
            scope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(1000))
            }
        }
        BottomSheetLayoutType.SEARCH ->{
            Log.d("layoutChanged","ssad")
            bottomSheetState = bottomSheetState.copy(layout = {BottomSheetContent()})
            scope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(1000))
            }
        }
        BottomSheetLayoutType.PROFILE -> {
            bottomSheetState = bottomSheetState.copy(layout = { MainBottomSheetLayout() })
            scope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded, anim = tween(1000))
            }
        }
        else -> bottomSheetState = bottomSheetState.copy(layout = {BottomSheetContent()})
    }

    ModalBottomSheetLayout(
        content = {
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
                    BottomNavigationBar(
                        showAddTask ={currentBottomSheetLayout = currentBottomSheetLayout.copy(type = BottomSheetLayoutType.ADD_TASK)} ,
                        showSearch = {currentBottomSheetLayout = currentBottomSheetLayout.copy(type = BottomSheetLayoutType.SEARCH)},
                        showProfile = {currentBottomSheetLayout = currentBottomSheetLayout.copy(type = BottomSheetLayoutType.PROFILE)},
                        showNotification = {currentBottomSheetLayout = currentBottomSheetLayout.copy(type = BottomSheetLayoutType.NOTIFICATIONS)}
                    )
                }
            )
        },
        sheetContent ={
            Column(modifier = Modifier.heightIn(min = 1.dp)) {
                bottomSheetState.layout()
            }
        } ,
        sheetState = sheetState
        )

//    BottomSheet()

}
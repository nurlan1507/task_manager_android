package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun  BottomSheet(){
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }


    ModalBottomSheetLayout(sheetState= sheetState,sheetContent = {BottomSheetContent()}) {
            val scope = rememberCoroutineScope()
            Box(modifier = Modifier.fillMaxSize()){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .clickable {
                            showModalSheet.value = !showModalSheet.value
                            scope.launch {
                                sheetState.show()
                            }
                        }
                )
            }


    }
}



@Composable
fun BottomSheetContent(){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Modal Bottom Sheet",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.White)
            Divider(
                modifier = Modifier.padding(5.dp),
                color = Color.White)
            Text(
                text = "Greetins",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier.padding(10.dp))
        }
    }

package com.nurlan1507.task_manager_mobile.ui_components.create_project_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectEvent
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.global_components.TopBar
import com.nurlan1507.task_manager_mobile.ui_components.create_project_screen.bottom_sheet_layouts.ColorSelectionBottomSheetLayout
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CreateProjectScreen(navController: NavController, projectViewmodel: ProjectViewmodel = hiltViewModel()) {
    val state = projectViewmodel.textFieldState.value
    val colorSelectionSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing)
    )
    val scope = rememberCoroutineScope()
    val isFavorite = remember{
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title ={ Text(text = "Создать", color = Color.Black, style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold) } ,
                modifier = Modifier.padding(
                    horizontal = 15.dp
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon(Icons.Default.ArrowBack,"back")}
                    },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.KeyboardArrowDown, "Create")
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = {
                        projectViewmodel.onEvent(ProjectEvent.ProjecTitleEntered(it))
                    },
                    label = {
                        Text(
                            text = "Название",
                            style = MaterialTheme.typography.body2,
                            color = Color(0xFF5E97FF)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color(0xFF5E97FF),
                        focusedBorderColor = Color(0xFF5E97FF),
                        unfocusedBorderColor = Color(0xFF5E97FF)
                    ),
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column() {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ) {
                        scope.launch {
                            colorSelectionSheetState.show()
                        }
                    }) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Google",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 4.dp),
                                tint = Color(state.color.color)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column(
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Text(text = "Цвет", style = MaterialTheme.typography.body1)
                                Text(text = state.color.name, style = MaterialTheme.typography.body1)
                            }
                        }
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ) {
                        isFavorite.value = !isFavorite.value
                    }) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            Row() {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Google",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(start = 4.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Избранное", style = MaterialTheme.typography.body1)
                            }
                            Switch(
                                checked = isFavorite.value,
                                onCheckedChange = { isFavorite.value = !isFavorite.value},
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFF5E97FF),
                                    checkedTrackColor = Color(0xFFA0BCF0),
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.Gray
                                )
                            )
                        }
                    }
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.title.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5E97FF),
                    contentColor = Color.White,
                    disabledBackgroundColor = Color(0xFFA0BCF0)
                ),
                onClick = {
                    projectViewmodel.onEvent(ProjectEvent.CreateProjectNetwork())
                    navController.navigateUp()
                }) { Text(text = "Создать проект", style = MaterialTheme.typography.body1) }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            ColorSelectionBottomSheetLayout(projectViewModel = projectViewmodel)
        },
        sheetState = colorSelectionSheetState
    ) {

    }


}
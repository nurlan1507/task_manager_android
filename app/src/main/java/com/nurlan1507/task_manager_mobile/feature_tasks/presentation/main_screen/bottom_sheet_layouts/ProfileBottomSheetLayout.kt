package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenDynamicNavigationOption
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.utils.MainScreenNavigationOption

@Composable
fun MainBottomSheetLayout() {
    val navigationOptions = listOf(
        MainScreenDynamicNavigationOption(MainScreenNavigationOption.IncomingTasks, 1),
        MainScreenDynamicNavigationOption(MainScreenNavigationOption.TodayTasks, 3),
        MainScreenDynamicNavigationOption(MainScreenNavigationOption.UpcomingTasks, 7),
        MainScreenDynamicNavigationOption(MainScreenNavigationOption.Filter, 0)
    )
    var showProjects by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp, horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(15.dp)
                .height(5.dp)
                .background(color = Color.Gray)
                .clip(RoundedCornerShape(5))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50))
                            .background(color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(
                            text = "Имя пользователя",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "почта пользователя@mail",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(Icons.Default.Settings, "Settings", tint = Color.Black)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                navigationOptions.map {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                        }) {
                        Row(modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = it.navOption.icon),
                                contentDescription = "Google",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 4.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = it.navOption.title, style = MaterialTheme.typography.body1)
                        }
                        Text(
                            text = it.dynamicField.toString(),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Проекты", fontSize = 21.sp, fontWeight = FontWeight.SemiBold,)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Использовано 1/5",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.background(color = Color.LightGray)
                    )
                }
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Add, "create a project")
                    }
                    IconButton(onClick = { showProjects = !showProjects }) {
                        Icon(
                            imageVector = if (showProjects == false) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            "show projects"
                        )
                    }
                }
            }
                Column {
                    AnimatedVisibility(
                        visible = showProjects,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                }) {
                                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                                    Canvas(modifier = Modifier.size(20.dp)) {
                                        drawCircle(
                                            color = Color.Gray,
                                            center = Offset(size.width / 2, size.height / 2),
                                            radius = size.width / 2
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(text = "Работа", style = MaterialTheme.typography.body1)
                                }
                            }

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                }) {
                                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Google",
                                        tint = Color.Black
                                    )
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "Настройка проектов",
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }

                }

        }

    }
}

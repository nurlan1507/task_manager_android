package com.nurlan1507.task_manager_mobile.ui_components.create_project_screen.bottom_sheet_layouts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectColor
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectEvent
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectTextFieldState
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColorSelectionBottomSheetLayout(projectViewModel:ProjectViewmodel){
    val colors = Project.Companion.projectColors
    val state = projectViewModel.textFieldState.value
    Box(modifier = Modifier.fillMaxWidth()){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(15.dp)
                .height(5.dp)
                .background(color = Color.Gray)
                .clip(RoundedCornerShape(5))
        )
        LazyColumn(){
            item { 
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(colors){color->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(if (color == state.color) Color.LightGray else Color.Transparent)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            projectViewModel.onEvent(ProjectEvent.ProjectColorSelected(color    ))
                        }
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight( )
                        .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color(color.color))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = color.name, style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    }
}
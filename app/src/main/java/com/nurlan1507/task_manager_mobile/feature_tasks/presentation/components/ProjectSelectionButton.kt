package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project

@Composable
fun ProjectSelectionButton(projectId:Long, projectList:List<Project>, onProjectSelected:(Long)->Unit) {
    val currentProject = projectList.find { it.projectId == projectId }
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
        .clickable {
            showDropdownMenu = true
        }
    ){
        Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)){
            if(projectId.equals(11)){
                Icon(painter = painterResource(id = R.drawable.incoming_icon), contentDescription = "Срок выполнения", tint = Color.Unspecified)
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = "Входящие" , style = MaterialTheme.typography.body2, color = Color.Gray)
            }else{
                Canvas(modifier = Modifier.size(20.dp)) {
                    drawCircle(
                        color = Color.Gray,
                        center = Offset(size.width / 2, size.height / 2),
                        radius = size.width / 2
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
                Text(text =currentProject?.title.toString() , style = MaterialTheme.typography.body2, color = Color.Gray)
            }
            Spacer(modifier = Modifier.width(3.dp))
            Icon(Icons.Default.ArrowDropDown, "dropdown")
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(0.5f),
            expanded = showDropdownMenu,
            onDismissRequest = { showDropdownMenu = false }
        ) {
            projectList.mapIndexed { index, project ->
                DropdownMenuItem(onClick = {
                    onProjectSelected(project.projectId!!)
                    showDropdownMenu = false
                }) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()){
                        Row(modifier = Modifier.align(Alignment.CenterStart)) {
                            if(project.projectId!!.equals(11)){
                                Icon(
                                    painter = painterResource(id = R.drawable.incoming_icon),
                                    contentDescription = "Google",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(start = 4.dp),
                                    tint = Color.Unspecified
                                )
                            }else{
                                Canvas(modifier = Modifier.size(20.dp)) {
                                    drawCircle(
                                        color = Color.Gray,
                                        center = Offset(size.width / 2, size.height / 2),
                                        radius = size.width / 2
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = project.title,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.nurlan1507.task_manager_mobile.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    projectViewModel:ProjectViewmodel = hiltViewModel(),
    tasksViewModel: TasksViewModel = hiltViewModel()
    // TODO: support overflow menu here with the remainder of the list
){
    var menuExpanded by remember { mutableStateOf(false) }
    val tasksState = tasksViewModel.tasksState.value
    val projectState = projectViewModel.projectState.value
    val title = if(projectState.currentProject !=null)  projectState.currentProject.title else tasksState.currentCategory.title
    TopAppBar(
        title ={Text(text =  title, color = Color.Black, fontWeight = FontWeight.SemiBold)} ,
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .background(Color(0xFF5E97FF)),
        actions = {
            IconButton(onClick = { menuExpanded = true }) {
                Icon(Icons.Default.MoreVert,"more")
            }
            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }, modifier = Modifier.width(225.dp)) {
                if(projectState.currentProject !=null){
                    DropdownMenuItem(leadingIcon ={Icon(Icons.Default.Email,"get a link")}, onClick = { /* TODO */ }, text = {Text("Получить ссылку", fontSize = 17.sp, fontWeight = FontWeight.SemiBold) }, colors = MenuDefaults.itemColors(), modifier = Modifier.align(Alignment.Start))
                    DropdownMenuItem(leadingIcon = {Icon(Icons.Default.Edit,"edit")}, onClick = { /* TODO */ }, text = {Text("редактировать",fontSize = 17.sp, fontWeight = FontWeight.SemiBold)},modifier = Modifier.align(Alignment.Start))
                    DropdownMenuItem(leadingIcon = {Icon(Icons.Default.Add,"edit")}, onClick = { /* TODO */ }, text = {Text("добавить раздел",fontSize = 17.sp, fontWeight = FontWeight.SemiBold)},modifier = Modifier.align(Alignment.Start))
                    DropdownMenuItem(leadingIcon = {Icon(Icons.Default.Add,"edit")}, onClick = { /* TODO */ }, text = {Text("добавить раздел",fontSize = 17.sp, fontWeight = FontWeight.SemiBold)},modifier = Modifier.align(Alignment.Start))
                }else{
                    DropdownMenuItem(leadingIcon ={Icon(Icons.Default.Settings,"settings")}, onClick = { /* TODO */ }, text = {Text("Отображение", fontSize = 17.sp, fontWeight = FontWeight.SemiBold) }, colors = MenuDefaults.itemColors(), modifier = Modifier.align(Alignment.Start))
                    DropdownMenuItem(leadingIcon = {Icon(Icons.Default.List,"list")}, onClick = { /* TODO */ }, text = {Text("Выбрать задачи",fontSize = 17.sp, fontWeight = FontWeight.SemiBold)},modifier = Modifier.align(Alignment.Start))
                }
            }
        },
    )
}
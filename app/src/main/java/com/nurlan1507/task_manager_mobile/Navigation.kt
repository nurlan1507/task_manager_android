    package com.nurlan1507.task_manager_mobile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.DateSelectionScreen
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.MainScreen
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.ui_components.sign_in.SignInScreen
import com.nurlan1507.task_manager_mobile.utils.Screen
import com.nurlan1507.task_manager_mobile.utils.rememberWindowSize

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(userViewModel: UserViewModel,tasksViewModel: TasksViewModel, projectViewmodel: ProjectViewmodel){
    val navController = rememberNavController()
    val window = rememberWindowSize()
    NavHost(navController = navController, startDestination = Screen.SignInScreen.route){
        composable(Screen.SignInScreen.route){
            SignInScreen(userViewModel = userViewModel, window = window, navController = navController, projectViewModel = projectViewmodel)
        }
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController, windowSize =window, tasksViewModel = tasksViewModel, projectViewmodel = projectViewmodel, userViewModel = userViewModel)
        }
        composable(Screen.DateSelectionScreen.route){
            DateSelectionScreen(tasksViewModel = tasksViewModel)
        }
    }
}
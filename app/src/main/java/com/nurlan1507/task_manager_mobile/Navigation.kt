package com.nurlan1507.task_manager_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.feature_users.presentation.sign_in.SignInScreen
import com.nurlan1507.task_manager_mobile.utils.Screen
import com.nurlan1507.task_manager_mobile.utils.rememberWindowSize

@Composable
fun Navigation(userViewModel: UserViewModel){
    val navController = rememberNavController()
    val window = rememberWindowSize()
    NavHost(navController = navController, startDestination = Screen.SignInScreen.route){
        composable(Screen.SignInScreen.route){
            SignInScreen(userViewModel = userViewModel, window = window)
        }
    }
}
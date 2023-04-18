package com.nurlan1507.task_manager_mobile.utils

sealed class Screen(val route:String){
    object SignInScreen:Screen("signIn")
}
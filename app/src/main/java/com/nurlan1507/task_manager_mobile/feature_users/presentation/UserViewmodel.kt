package com.nurlan1507.task_manager_mobile.feature_users.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.UserUseCases


class UserViewModel(val appObj: Application):AndroidViewModel(appObj){
}
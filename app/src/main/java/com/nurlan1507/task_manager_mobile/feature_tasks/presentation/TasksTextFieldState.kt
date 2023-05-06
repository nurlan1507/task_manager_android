package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import android.icu.text.CaseMap.Title

data class TasksTextFieldState(
    val title: String = "",
    val description:String = "",
    val finishDate:Long? = System.currentTimeMillis()/1000  ,
    val projectId:Int = 1
)
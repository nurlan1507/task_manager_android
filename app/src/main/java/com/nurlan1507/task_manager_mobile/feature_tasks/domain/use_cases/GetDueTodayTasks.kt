package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import java.time.LocalDate
import java.time.ZoneOffset

class GetDueTodayTasks(private val repository:TasksRepositoryImpl) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke():List<TaskWithProject>{
        val dueDate = LocalDate.now().atTime(23,59,0).toInstant(ZoneOffset.UTC).epochSecond
        Log.d("TodayDate",dueDate.toString())
        return repository.getTasksDueToday(dueDate)
    }
}
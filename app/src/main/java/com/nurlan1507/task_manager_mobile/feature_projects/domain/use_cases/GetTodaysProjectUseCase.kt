package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.nurlan1507.task_manager_mobile.feature_projects.data.repository.ProjectRepositoryImpl
import java.time.LocalDate
import java.time.ZoneOffset

class GetTodaysProjectUseCase(private val repository: ProjectRepositoryImpl) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(){
        val localDate = LocalDate.now().atTime(23,59).toInstant(ZoneOffset.UTC).epochSecond
        val a = repository.getProjectsWithTasksDueToday(localDate)
        Log.d("todaytasks", a.toString())
    }
}
package com.nurlan1507.task_manager_mobile.feature_tasks.data

import androidx.room.Dao
import androidx.room.Insert
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import retrofit2.http.GET

@Dao
interface TasksDao{
    @Insert
    suspend fun insertTask(task:Task):Long

}

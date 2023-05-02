package com.nurlan1507.task_manager_mobile.feature_tasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject

@Dao
interface TasksDao{
    @Insert
    suspend fun insertTask(task:Task):Long

    @Query("SELECT * FROM task where project_id=:projectId")
    suspend fun getTasks(projectId:String): List<TaskWithProject>

}

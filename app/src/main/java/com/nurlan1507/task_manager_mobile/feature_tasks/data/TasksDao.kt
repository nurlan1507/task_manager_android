package com.nurlan1507.task_manager_mobile.feature_tasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject

@Dao
interface TasksDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task:Task):Long

    @Transaction
    @Query("SELECT * FROM task where project_id=:projectId")
    suspend fun getTasks(projectId:Int): List<TaskWithProject>

    @Query("SELECT * from task t join project p on p.id = t.project_id where t.finish_date = :today")
    suspend fun getTasksDueToday(today:Long):List<TaskWithProject>

    @Query("SELECT * FROM task where task_id=:taskId")
    suspend fun getTask(taskId:Long):Task

    @Delete
    suspend fun deleteTask(task:Task)
}

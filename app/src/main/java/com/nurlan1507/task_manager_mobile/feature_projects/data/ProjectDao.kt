package com.nurlan1507.task_manager_mobile.feature_projects.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import retrofit2.http.GET

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProject(project: Project):Long

    @Query("SELECT * FROM project WHERE user_id=:userId")
    suspend fun getProjects(userId:String):List<Project>

    @Query("SELECT * FROM project where id=:projectId")
    suspend fun getProject(projectId:Int): Project

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Transaction
    suspend fun insertProjectWithTasks(project: Project, tasks: List<Task>) {
        createProject(project)
        tasks.forEach { task ->
            insertTask(task.apply { projectId = project.projectId!! })
        }
    }

    @Query("SELECT DISTINCT * FROM project p join task t on p.id=project_id")
    suspend fun getTodayDueTasks():List<ProjectWithTasks>
}
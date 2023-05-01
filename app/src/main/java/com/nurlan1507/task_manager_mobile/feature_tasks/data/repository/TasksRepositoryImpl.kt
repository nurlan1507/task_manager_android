package com.nurlan1507.task_manager_mobile.feature_tasks.data.repository

import com.google.android.gms.tasks.Tasks
import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_tasks.data.TasksDao
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.ProjectWithTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val taskDao:TasksDao, private val remoteDataSource: TasksRemoteDataSource):TasksRepository {
    override suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    override suspend fun getTasks(projectId: String): List<Task> {
        return taskDao.getTasks(projectId = projectId)
    }

}
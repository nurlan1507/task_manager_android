package com.nurlan1507.task_manager_mobile.feature_tasks.data.repository

import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_tasks.data.TasksDao
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.TaskWithProject
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.repositry.TasksRepository

class TasksRepositoryImpl(private val taskDao:TasksDao, private val remoteDataSource: TasksRemoteDataSource):TasksRepository {
    override suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    override suspend fun getTask(taskId: Long): Task {
        return taskDao.getTask(taskId)
    }

    override suspend fun getTasks(projectId: Int): List<TaskWithProject> {
        return taskDao.getTasks(projectId = projectId)
    }

    override suspend fun getTasksDueToday(date:Long): List<TaskWithProject> {
        return taskDao.getTasksDueToday(date)
    }


}
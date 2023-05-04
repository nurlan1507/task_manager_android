package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import android.util.Log
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectArrApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository
import com.nurlan1507.task_manager_mobile.feature_tasks.data.TasksDao
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase

class GetProjectsUseCase(private val repository: ProjectRepository?) {
    suspend operator fun invoke(): NetworkResult<ProjectArrApiResponse>? {
        val projects = repository?.getProjectsNetwork()
        Log.d("getProjects", projects?.code.toString())
        if (projects?.code == 200) {
            val projectList = projects.data!!.projects
            projectList.forEach {
                repository?.insertProjectWithTasks(
                    project = Project(it.projectId, it.title, it.user_id),
                    tasks = it.tasks
                )

            }
        }
        return projects
    }
}
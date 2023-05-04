package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

import android.util.Log
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectArrApiResponse
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_projects.domain.repository.ProjectRepository
import com.nurlan1507.task_manager_mobile.restService.NetworkResult

class GetProjectsUseCase(private val repository: ProjectRepository?){
    suspend operator fun invoke():NetworkResult<ProjectArrApiResponse>?{
            val projects = repository?.getProjectsNetwork()
            Log.d("getProjects", projects?.code.toString())
            if(projects?.code==200){
                val projectList = projects.data!!.projects
                if(projectList!=null){
                    projectList.forEach{
                        repository?.insertProject(Project(it.projectId,it.title,it.user_id))
                        it.tasks.forEach {

                        }
                    }
                }
            }
            return projects


    }
}
package com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases

data class ProjectUseCases (
    val getProjectUseCase:GetProjectUseCase,
    val createProjectUseCase:CreateProjectUseCase,
    val createProjectNetworkUseCase: CreateProjectNetworkUseCase,
    val getProjectsNetworkUseCase: GetProjectsNetworkUseCase,
    val getProjectsUseCase: GetProjectsUseCase,
    val getTodayTaskUseCase:GetTodaysProjectUseCase,
)
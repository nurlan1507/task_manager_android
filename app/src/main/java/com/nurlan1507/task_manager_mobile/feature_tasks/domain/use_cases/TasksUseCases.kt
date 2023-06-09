package com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases

data class TasksUseCases (
    val createTaskUseCase:CreateTaskUseCase,
    val getTasksUseCase:GetTasksUseCase,
    val getDueTodayTasks:GetDueTodayTasks,
    val deleteTaskUseCase:DeleteTaskUseCase,
    //network
    val getTasksNetworkUseCase: GetTasksNetworkUseCase,
    val createTaskNetworkUseCase: CreateTaskNetworkUseCase
    )
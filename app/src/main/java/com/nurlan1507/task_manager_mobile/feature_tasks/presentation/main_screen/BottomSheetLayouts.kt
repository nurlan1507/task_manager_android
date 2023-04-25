package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen

enum class BottomSheetLayoutType{
    SEARCH,NOTIFICATIONS, PROFILE, ADD_TASK
}

data class CurrentBottomSheetLayout(
    var type:BottomSheetLayoutType?,
)


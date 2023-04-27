package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen

//enum class BottomSheetLayoutType{
//    SEARCH,NOTIFICATIONS, PROFILE, ADD_TASK
//}

sealed class BottomSheetLayoutType{
    object Search:BottomSheetLayoutType()
    object Nofifications:BottomSheetLayoutType()
    object Profile:BottomSheetLayoutType()
    object AddTask:BottomSheetLayoutType()

}
data class CurrentBottomSheetLayout(
    var type:BottomSheetLayoutType?,
)


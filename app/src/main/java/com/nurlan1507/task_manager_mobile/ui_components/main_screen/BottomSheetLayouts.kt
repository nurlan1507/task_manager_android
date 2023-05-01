package com.nurlan1507.task_manager_mobile.ui_components.main_screen

//enum class BottomSheetLayoutType{
//    SEARCH,NOTIFICATIONS, PROFILE, ADD_TASK
//}

sealed class BottomSheetLayoutType{
    object Search: BottomSheetLayoutType()
    object Nofifications: BottomSheetLayoutType()
    object Profile: BottomSheetLayoutType()
    object AddTask: BottomSheetLayoutType()

    object DateSelection: BottomSheetLayoutType()

}
data class CurrentBottomSheetLayout(
    var type: BottomSheetLayoutType?,
)


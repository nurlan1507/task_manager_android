package com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils

sealed class DateSelectionMenu {
    object Tomorrow : DateSelectionMenu()
    object Weekend: DateSelectionMenu()
    object NoDate: DateSelectionMenu()
}


val selectionOptions = listOf(
    DateSelectionMenu.NoDate,
    DateSelectionMenu.Tomorrow,
    DateSelectionMenu.Weekend
)
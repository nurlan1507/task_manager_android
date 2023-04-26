package com.nurlan1507.task_manager_mobile.feature_tasks.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

data class BottomSheetLayoutState @OptIn(ExperimentalMaterialApi::class) constructor(
    val layout: @Composable () -> Unit ={ },

    )
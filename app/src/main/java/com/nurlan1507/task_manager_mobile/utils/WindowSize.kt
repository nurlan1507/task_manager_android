package com.nurlan1507.task_manager_mobile.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class WindowSize(
    val width: WindowType,
    val height: WindowType
){
    var headerSize: TextUnit = 28.sp
    var textSize: TextUnit = 22.sp
    var padding: Dp = 30.dp
    var spacer: Dp = 8.dp
    var elemSpacer:Dp = 36.dp
    var fieldHeight: Dp = 56.dp
    var btnVerticalPadding: Dp = 20.dp
    var btnTextSize: TextUnit = 13.sp
    var btnHeight:Dp = 50.dp
    init{
        if(this.width == WindowType.Normal){
            headerSize = 21.sp
            textSize = 15.sp
            padding =  20.dp
            spacer = 10.dp
            fieldHeight = 56.dp
            btnVerticalPadding = 14.dp
            btnTextSize = 13.sp
            elemSpacer = 20.dp

        }else if(this.width == WindowType.Large){
            headerSize = 28.sp
            textSize = 22.sp
            padding =  40.dp
            spacer = 20.dp
            btnVerticalPadding = 40.dp
            btnTextSize = 16.sp
            elemSpacer = 36.dp
        }

    }
}

enum class WindowType { Normal, Large, ExtraLarge }


@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = getScreenWidth(screenWidth),
        height = getScreenHeight(screenHeight)
    )
}

fun getScreenWidth(width: Int): WindowType = when {
    width >  600 -> WindowType.Large
    width < 600-> WindowType.Normal
    else -> WindowType.ExtraLarge
}

fun getScreenHeight(height: Int): WindowType = when {
    height < 480 -> WindowType.Normal
    height < 900 -> WindowType.Large
    else -> WindowType.ExtraLarge
}

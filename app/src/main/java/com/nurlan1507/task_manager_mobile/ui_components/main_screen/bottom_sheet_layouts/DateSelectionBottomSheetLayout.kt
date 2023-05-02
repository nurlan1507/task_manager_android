package com.nurlan1507.task_manager_mobile.ui_components.main_screen.bottom_sheet_layouts

import android.os.Build
import android.util.Log
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.DateSelectionView
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.DateSelectionMenu
import com.nurlan1507.task_manager_mobile.ui_components.main_screen.utils.daysOfTheWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectionBottomSheetLayout(tasksViewModel: TasksViewModel) {
    val fieldState = tasksViewModel.fieldState.value
    val tasksState = tasksViewModel.tasksState.value
    val currentDate = fieldState.finishDate.let {
        if(it!=null) Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate()
        else null
    }

    val nextDayLocalDate = currentDate?.plusDays(1)
    val nextDay = nextDayLocalDate?.dayOfWeek?.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val weekend = tasksViewModel.getNextWeekendDays()
    val weekendString = weekend.joinToString {
        it.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(15.dp)
                .height(5.dp)
                .background(color = Color.Gray)
                .clip(RoundedCornerShape(5))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    tasksViewModel.onEvent(TasksEvent.EnteredFinishDate(nextDayLocalDate?.atTime(23,59)?.toEpochSecond(ZoneOffset.UTC)))
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Google",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text =if(currentDate==null)"Без даты" else currentDate.format(DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault())), style = MaterialTheme.typography.body1)
                }
            }
            Divider()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(if(tasksState.dateSelectionOption== DateSelectionMenu.Tomorrow)Color.LightGray else Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    tasksViewModel.onEvent(TasksEvent.ChangeDateSelectionOption(DateSelectionMenu.Tomorrow))
                    tasksViewModel.onEvent(TasksEvent.EnteredFinishDate(nextDayLocalDate?.atTime(23,59)?.toEpochSecond(
                        ZoneOffset.UTC)))
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.next_day_icon),
                        contentDescription = "Завтра",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Завтра", style = MaterialTheme.typography.body1)
                }
                Text(text = nextDay?:"".toUpperCase(), style = MaterialTheme.typography.body1, modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -20.dp))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(if(tasksState.dateSelectionOption== DateSelectionMenu.Weekend)Color.LightGray else Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    tasksViewModel.onEvent(TasksEvent.ChangeDateSelectionOption(DateSelectionMenu.Weekend))
                    tasksViewModel.onEvent(TasksEvent.EnteredFinishDate( weekend[1].atTime(23,59).toEpochSecond(ZoneOffset.UTC)))
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.weekend_icon),
                        contentDescription = "weekend",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Выходные ", style = MaterialTheme.typography.body1)

                }
                Text(text = weekendString, style = MaterialTheme.typography.body1, modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -20.dp))

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(if(tasksState.dateSelectionOption== DateSelectionMenu.NoDate)Color.LightGray else Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    tasksViewModel.onEvent(TasksEvent.ChangeDateSelectionOption(DateSelectionMenu.NoDate))
                    tasksViewModel.onEvent(TasksEvent.EnteredFinishDate(null))
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.block_icon),
                        contentDescription = "block_icon",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Без Срока", style = MaterialTheme.typography.body1)
                }
            }

            DateSelectionView(currentDate) { timestamp ->
                tasksViewModel.onEvent(TasksEvent.ChangeDateSelectionOption(null))
                tasksViewModel.onEvent(TasksEvent.EnteredFinishDate(timestamp))
            }
        }
    }
}




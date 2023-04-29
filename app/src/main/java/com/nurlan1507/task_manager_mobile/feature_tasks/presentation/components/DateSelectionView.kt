package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.nurlan1507.task_manager_mobile.R
import java.lang.Math.ceil
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import kotlin.math.ceil

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectionView(onDateSelected: (LocalDate) -> Unit){
    val yearMothArr = mutableListOf<YearMonth>()
    val currentMonth = YearMonth.now()
    val currentYear = LocalDate.now().year
    for(month in currentMonth.monthValue..12){
        yearMothArr.add(YearMonth.of(currentYear, month))
    }
//    for(year in 2022..2023){
//        for(month in Month.values()){
//            yearMothArr.add(YearMonth.of(year,month))
//        }
//    }
    val daynames = listOf<String>("Mon","Tue","Wed","Thu","Fri","Sat","Sun")
    Column(
        modifier = Modifier.fillMaxWidth().scrollable(rememberScrollState(), orientation = Orientation.Vertical),
    ) {
        val daysOfWeek = DateFormatSymbols.getInstance().shortWeekdays
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (dayOfWeek in daynames) {
                Text(
                    text = dayOfWeek,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp)

                )
            }
        }
        yearMothArr.mapIndexed { index, yearMonth ->
            MonthView(
                month = yearMonth  ,
                onDateSelected = onDateSelected,
                modifier = if (index == 0) Modifier.padding(top = 8.dp) else Modifier,
                index = index
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthView(
    month: YearMonth,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    index:Int

){
    val currentDay = LocalDate.now().dayOfMonth

    Column(modifier = modifier) {

        Text(
            text = month.month.name,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        val firstDayOfMonth = month.atDay(1).dayOfWeek.value
        val daysInMonth = month.lengthOfMonth()
        val weeksInMonth = ceil((firstDayOfMonth + daysInMonth - 1) / 7f).toInt()
        if(index == 0){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                for (weekIndex in weeksInMonth/currentDay.toInt() until weeksInMonth) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (dayOfWeek in 1..7) {
                            val dayOfMonth = (weekIndex * 7) + dayOfWeek - firstDayOfMonth + 1
                            if (dayOfMonth > 0 && dayOfMonth <= daysInMonth) {
                                val date = month.atDay(dayOfMonth)
                                val isSelected = date == LocalDate.now()
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(CircleShape)
                                        .padding(vertical = 4.dp, horizontal = 4.dp)
                                        .background(color = if (isSelected) Color(0xFF5E97FF) else Color.Transparent)
                                ){
                                    Text(
                                        text = dayOfMonth.toString(),
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier
                                            .clickable { onDateSelected(date) }
                                            .align(Alignment.Center)
                                    )
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }else{
            for (weekIndex in 0 until weeksInMonth) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (dayOfWeek in 1..7) {
                        val dayOfMonth = (weekIndex * 7) + dayOfWeek - firstDayOfMonth + 1
                        if (dayOfMonth > 0 && dayOfMonth <= daysInMonth) {
                            val date = month.atDay(dayOfMonth)
                            val isSelected = date == LocalDate.now()
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(CircleShape)
                                    .padding(vertical = 4.dp, horizontal = 4.dp)
                                    .background(color = if (isSelected) Color(0xFF5E97FF) else Color.Transparent)
                            ){
                                Text(
                                    text = dayOfMonth.toString(),
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    style = MaterialTheme.typography.body1,
                                    modifier = Modifier
                                        .clickable { onDateSelected(date) }
                                        .align(Alignment.Center)
                                )
                            }

                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

    }

}
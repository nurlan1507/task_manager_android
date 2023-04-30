package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components

import android.icu.util.Calendar
import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import java.time.ZoneOffset
import java.util.Date
import kotlin.math.ceil

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectionView(currentDate:LocalDate, onDateSelected:(Long)->Unit){
    val ctx = LocalContext.current
    var selectedDate  = currentDate
    val calendar = remember { Calendar.getInstance() }
    calendar.time = Date.from(selectedDate.atStartOfDay().toInstant(ZoneOffset.UTC))
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.fillMaxWidth(),
        update = { views ->
            views.setOnDateChangeListener { calendarView, year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(year,month,day)
                val timestamp = calendar.timeInMillis
                onDateSelected(timestamp)
            }
        }
    )
}
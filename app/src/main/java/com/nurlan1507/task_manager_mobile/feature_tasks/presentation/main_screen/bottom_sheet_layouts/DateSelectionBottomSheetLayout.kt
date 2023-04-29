package com.nurlan1507.task_manager_mobile.feature_tasks.presentation.main_screen.bottom_sheet_layouts

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksEvent
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.components.DateSelectionView
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectionBottomSheetLayout(tasksViewModel: TasksViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp)
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(15.dp)
                .height(5.dp)
                .background(color = Color.Gray)
                .clip(RoundedCornerShape(5))
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {

                }) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight()) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Google",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "6 мая", style = MaterialTheme.typography.body1)
                }
            }
            Divider()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {

                }) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight()) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Google",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "6 мая", style = MaterialTheme.typography.body1)
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {

                }) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight()) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Google",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "6 мая", style = MaterialTheme.typography.body1)
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {

                }) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight()) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Google",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "6 мая", style = MaterialTheme.typography.body1)
                }
            }
            Box(){
                DateSelectionView(onDateSelected = {})
            }
        }
    }
}
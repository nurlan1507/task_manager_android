package com.nurlan1507.task_manager_mobile.feature_users.presentation.components

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.ui.theme.Typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SignInCarousel(){
    val textList = listOf<String>(
        "Организуйте свою и личную жизнь",
        "Планируйте ваши рабочие дни",
        "Развивайтесь вместе")
    val imageList = listOf<Int>(R.drawable.logo, R.drawable.plan, R.drawable.team)

    val state = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()


//    LaunchedEffect(state.currentPage){
//        delay(6000)
//        var newPosition = state.currentPage + 1
//        if(newPosition > textList.lastIndex){
//            newPosition = 0
//        }
//        state.animateScrollToPage(newPosition)
//    }
        HorizontalPager(count = textList.size, state = state) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(450.dp)
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RectangleShape),
                        alignment = Alignment.Center,
                        painter = painterResource(id = imageList[page]),
                        contentDescription = "image",
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = textList[page],
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        style = Typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
    }
    DotsIndicator(totalDots = 3, selectedIndex = state.currentPage, onClick = {
        coroutineScope.launch {
            state.animateScrollToPage(it)
        }
    })
}
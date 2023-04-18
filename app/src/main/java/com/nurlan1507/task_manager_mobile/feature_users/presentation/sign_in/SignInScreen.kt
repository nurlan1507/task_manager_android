package com.nurlan1507.task_manager_mobile.feature_users.presentation.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.R
import com.nurlan1507.task_manager_mobile.feature_users.presentation.components.SignInButton
import com.nurlan1507.task_manager_mobile.utils.WindowSize
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.nurlan1507.task_manager_mobile.feature_users.presentation.components.SignInCarousel
import com.nurlan1507.task_manager_mobile.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(window: WindowSize){
    val ctx = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 15.dp)) {
        Column(){
            SignInCarousel()
            Spacer(modifier = Modifier.height(20.dp))
            SignInButton(onClick = {}, imageVector = R.drawable.icon_google, text = "Войти через аккаунт Google")
            Spacer(modifier = Modifier.height(20.dp))
            SignInButton(onClick = {}, imageVector = R.drawable.facebook_logo, text = "Войти через аккаунт FaceBook")
        }
        Column(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.Bottom){
            Text(modifier = Modifier.fillMaxWidth(), text = "Продолжая с использованием указанных выше сервисов, вы согшаетесь с Условиями использования и Политикой конфиденциальности TaskManage", textAlign = TextAlign.Center, style = Typography.bodySmall, color = Color.Gray)
        }
    }
}
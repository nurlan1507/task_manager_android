package com.nurlan1507.task_manager_mobile.feature_users.presentation.sign_in

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.nurlan1507.task_manager_mobile.MainActivity
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserEvent
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.feature_users.presentation.components.SignInCarousel
import com.nurlan1507.task_manager_mobile.ui.theme.Typography
import com.nurlan1507.task_manager_mobile.utils.Screen
import com.nurlan1507.task_manager_mobile.utils.TokenManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(userViewModel: UserViewModel, window: WindowSize, navController: NavController){
    val ctx = LocalContext.current
    val activity = LocalContext.current as Activity


//    var resultCode by userViewModel.resultCode.

    var startGoogleSignInForResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            Log.d("googleAuth","asdasd")
                Toast.makeText(ctx,"asdasd", Toast.LENGTH_LONG).show()
                Log.d("googleAuth","asdasd")
                if(result.data!=null){
                    val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    if(task.isSuccessful){
                        userViewModel.onEvent(event = UserEvent.GoogleSignInEvent(task.result.id.toString(), task.result.displayName.toString(), task.result.email.toString()))}else{
                        Toast.makeText(ctx,"asdasd", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }


    val signInIntent = remember {
        (ctx as MainActivity).getGSO().signOut()
        (ctx as MainActivity).getGSO().signInIntent
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 15.dp)) {
        Column(){
            SignInCarousel()
            Spacer(modifier = Modifier.height(20.dp))
            SignInButton(onClick = {startGoogleSignInForResult.launch(signInIntent)}, imageVector = R.drawable.icon_google, text = "Войти через аккаунт Google")
            Spacer(modifier = Modifier.height(20.dp))
            SignInButton(onClick = {}, imageVector = R.drawable.facebook_logo, text = "Войти через аккаунт FaceBook")
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f), verticalArrangement = Arrangement.Bottom){
            Text(modifier = Modifier.fillMaxWidth(), text = "Продолжая с использованием указанных выше сервисов, вы согшаетесь с Условиями использования и Политикой конфиденциальности TaskManage", textAlign = TextAlign.Center, style = Typography.bodySmall, color = Color.Gray)
        }
    }
}
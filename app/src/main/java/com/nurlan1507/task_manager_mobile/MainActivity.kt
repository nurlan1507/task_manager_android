package com.nurlan1507.task_manager_mobile

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nurlan1507.task_manager_mobile.feature_projects.presentation.ProjectViewmodel
import com.nurlan1507.task_manager_mobile.feature_tasks.presentation.TasksViewModel
import com.nurlan1507.task_manager_mobile.feature_users.presentation.UserViewModel
import com.nurlan1507.task_manager_mobile.ui.theme.Task_manager_mobileTheme
import com.nurlan1507.task_manager_mobile.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        TokenManager.init(this)
//        val userViewModel: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        val tasksViewModel:TasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
//        val projectViewModel:ProjectViewmodel = ViewModelProvider(this).get(ProjectViewmodel::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            Task_manager_mobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }

    fun getGSO(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this,gso)
        return googleSignInClient
    }
}


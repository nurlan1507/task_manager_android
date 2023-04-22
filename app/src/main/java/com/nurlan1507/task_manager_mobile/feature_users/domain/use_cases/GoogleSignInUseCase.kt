package com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases

import android.util.Log
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthApiResponse
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.repository.UserRepository
import com.nurlan1507.task_manager_mobile.restService.NetworkResult
import com.nurlan1507.task_manager_mobile.utils.TokenManager

class GoogleSignInUseCase(
    val userRepository: UserRepositoryImpl
) {
    suspend operator fun invoke(google_id:String, username:String, email:String): NetworkResult<AuthApiResponse> {
        var result = userRepository.googleSignIn(google_id, username,email)
        Log.d("googleAutht", "${result.code} ${result.data?.data?.accessToken}")
        if(result.code == 200){
            TokenManager.refreshToken = result.data?.data?.refreshToken
            TokenManager.accessToken = result.data?.data?.accessToken
        }
        return result
    }
}
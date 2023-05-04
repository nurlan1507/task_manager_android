package com.nurlan1507.task_manager_mobile.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

object TokenManager {
    const val SHARED_PREFS_NAME = "task_manager"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"
    const val USER_ID_KEY = "user_id"

    private lateinit var  sharedPreferences: SharedPreferences

    fun init(ctx: Context) {
        sharedPreferences = ctx.applicationContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getAccessToken():String?{
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }
    fun setAccessToken(value:String?){
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, value).apply()
    }
    fun getRefreshToken():String?{
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }
    fun setRefreshToken(value:String){
        sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, value).apply()
    }

    fun getUserId():String?{
        return sharedPreferences.getString(USER_ID_KEY,null)
    }
    fun setUserId(id:String){
        sharedPreferences.edit().putString(USER_ID_KEY,id).apply()
    }


}
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

    lateinit var context: Context
    private lateinit var  sharedPreferences: SharedPreferences

    @SuppressLint("StaticFieldLeak")
    fun init(ctx: Context) {
        context = ctx.applicationContext
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
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
    @SuppressLint("StaticFieldLeak")
    fun setRefreshToken(value:String){
        sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, value).apply()
    }


}
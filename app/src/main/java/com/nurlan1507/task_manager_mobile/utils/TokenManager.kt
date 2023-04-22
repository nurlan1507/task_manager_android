package com.nurlan1507.task_manager_mobile.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

object TokenManager {
    const val SHARED_PREFS_NAME = "task_manager"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"

    lateinit var sharedPrefs:SharedPreferences
    lateinit var sharedPrefsEditor:Editor

    var accessToken:String?
        get() = sharedPrefs.getString(ACCESS_TOKEN_KEY,null)
        set(value) {sharedPrefsEditor.putString(ACCESS_TOKEN_KEY, value)}
    var refreshToken:String?
        get() = sharedPrefs.getString(REFRESH_TOKEN_KEY,null)
        set(value) {sharedPrefsEditor.putString(REFRESH_TOKEN_KEY, value)}


    fun init(ctx:Context){
        sharedPrefs = ctx.getSharedPreferences(SHARED_PREFS_NAME,Activity.MODE_PRIVATE)
        sharedPrefsEditor = sharedPrefs.edit()
    }


}
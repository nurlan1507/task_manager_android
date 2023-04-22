package com.nurlan1507.task_manager_mobile.feature_users.presentation

sealed class UserEvent {
    data class GoogleSignInEvent(val id:String, val username:String, val email:String):UserEvent()
    data class FacebookSignInEvent(val id:String):UserEvent()

}
package com.nurlan1507.task_manager_mobile.feature_users.domain.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friend(
    @ColumnInfo(name = "user_id")
    @PrimaryKey
    val userId:String="",
    @ColumnInfo(name= "email")
    val email:String?=null,
    @ColumnInfo(name = "username")
    val username:String?=null
)

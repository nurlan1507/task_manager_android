package com.nurlan1507.task_manager_mobile.feature_users.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
data class User(
    @SerializedName("id")
    @ColumnInfo(name = "user_id")
    @PrimaryKey
    val userId:String,
    @SerializedName("email")
    @ColumnInfo(name= "email")
    val email:String,
    @SerializedName("username")
    @ColumnInfo(name = "username")
    val username:String,
     val accessToken:String="",
     val refreshToken:String=""
)

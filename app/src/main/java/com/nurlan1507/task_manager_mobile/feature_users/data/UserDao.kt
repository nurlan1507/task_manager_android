package com.nurlan1507.task_manager_mobile.feature_users.data

import androidx.room.Dao
import androidx.room.Query
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE user_id=:userId")
    fun getUser(userId:String): User?

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user:User)
}

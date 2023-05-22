package com.nurlan1507.task_manager_mobile.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurlan1507.task_manager_mobile.feature_projects.data.ProjectDao
import com.nurlan1507.task_manager_mobile.feature_tasks.data.TasksDao
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task
import com.nurlan1507.task_manager_mobile.feature_users.data.UserDao
import com.nurlan1507.task_manager_mobile.feature_users.domain.models.User


//@Database(
//    entities = [User::class],
//    version = 1
//)
//abstract class TaskManagerDatabase:RoomDatabase() {
//    abstract val userDao: UserDao
//    companion object{
//        const val DATABASE_NAME = "task_manager_db"
//    }
//}

@Database(
    entities = [User::class, Project::class, Task::class],
    version = 3,
    exportSchema = false
)
abstract class TaskManagerDatabase: RoomDatabase() {
    abstract fun  userDao():UserDao
    abstract fun taskDao():TasksDao
    abstract fun projectDao():ProjectDao
    companion object{
        const val DATABASE_NAME = "room_db"
//        @Volatile
//        private var INSTANCE: TaskManagerDatabase? = null
//        fun getDatabase(context: Context): TaskManagerDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(context.applicationContext, TaskManagerDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                return instance
//            }
//        }
    }
}

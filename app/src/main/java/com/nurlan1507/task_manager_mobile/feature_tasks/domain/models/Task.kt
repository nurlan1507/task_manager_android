package com.nurlan1507.task_manager_mobile.feature_tasks.domain.models

import androidx.annotation.NonNull
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.util.TableInfo
import com.nurlan1507.task_manager_mobile.feature_projects.domain.models.Project


@Entity(tableName = "task", foreignKeys = [])
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    @NonNull
    val taskId:Long = 0,
    val title:String?=null,
    val description:String?=null,
    @ColumnInfo(name = "finish_date")
    val finishDate:Long?=null,
    @ColumnInfo(name = "project_id")
    var projectId:Long? = null,
    @ColumnInfo(name = "status")
    var status:Long?=0,

)


data class TaskWithProject(
    @Embedded val task:Task,
    @Relation(
        parentColumn = "project_id",
        entityColumn = "id"
    )
    val project: Project,
)


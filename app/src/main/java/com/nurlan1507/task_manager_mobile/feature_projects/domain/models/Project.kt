package com.nurlan1507.task_manager_mobile.feature_projects.domain.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.google.gson.annotations.SerializedName
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task

@Entity(tableName = "project")
data class Project(
    @PrimaryKey
    @ColumnInfo(name="id")
    @SerializedName("projectId")
    val projectId:Int,
    val title:String,
    @ColumnInfo(name = "user_id")
    val userId:String,
    @ColumnInfo(name = "icon_url")
    val iconUrl:String
)

data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    @SerializedName("tasks")
    val tasks: List<Task>
    )

data class ProjectWithTasksR(
    val projectId:Int,
    val title:String,
    val user_id:String,
    @SerializedName("tasks")
    val tasks: List<Task>
)
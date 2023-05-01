package com.nurlan1507.task_manager_mobile.feature_projects.domain.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.models.Task

@Entity(tableName = "project")
data class Project(
    @PrimaryKey
    @ColumnInfo(name="id")
    val projectId:String,
    val title:String,

)

data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val tasks: List<Task>
    )
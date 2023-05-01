package com.nurlan1507.task_manager_mobile.feature_tasks.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo


@Entity(tableName = "task", foreignKeys = [])
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    val taskId:Long?=null,
    val title:String?=null,
    val description:String?=null,
    @ColumnInfo(name = "finish_date")
    val finishDate:Long?=null,
    @ColumnInfo(name = "project_id")
    val projectId:String ="1"
)


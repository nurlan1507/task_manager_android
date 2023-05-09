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
    val projectId:Long ?=null,
    val title:String,
    @ColumnInfo(name = "user_id")
    val userId:String="",
    @ColumnInfo(name = "icon_url")
    val iconUrl:String
){
    companion object{
        val projectColors = listOf(
            ProjectColor(0xFFFF1744, "Красный"),
            ProjectColor(0xFFF50057, "Розовый"),
            ProjectColor(0xFFD500F9, "Фиолетовый"),
            ProjectColor(0xFF651FFF, "Сиреневый"),
            ProjectColor(0xFF3D5AFE, "Синий"),
            ProjectColor(0xFF2979FF, "Голубой"),
            ProjectColor(0xFF00B0FF, "Светло-голубой"),
            ProjectColor(0xFF00E5FF, "Бирюзовый"),
            ProjectColor(0xFF1DE9B6, "Зеленый"),
            ProjectColor(0xFF00C853, "Салатовый"),
            ProjectColor(0xFF64DD17, "Лаймовый"),
            ProjectColor(0xFFFFD600, "Желтый"),
            ProjectColor(0xFFFFAB00, "Оранжевый"),
            ProjectColor(0xFFFF6D00, "Темно-оранжевый"),
            ProjectColor(0xFFFF3D00, "Красно-оранжевый"),
            ProjectColor(0xFFBDBDBD, "Серый"),
            ProjectColor(0xFF616161, "Темно-серый"),
            ProjectColor(0xFF455A64, "Сине-серый"),
            ProjectColor(0xFF212121, "Черный"),
            ProjectColor(0xFFFFFFFF, "Белый"),
        )    }
}

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
    val projectId:Long,
    val title:String,
    val user_id:String,
    @SerializedName("tasks")
    val tasks: List<Task>
)


data class ProjectColor(
    val color:Long,
    val name:String
)
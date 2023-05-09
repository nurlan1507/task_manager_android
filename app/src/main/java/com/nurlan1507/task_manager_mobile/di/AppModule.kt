package com.nurlan1507.task_manager_mobile.di

import android.app.Application
import androidx.room.Room
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_projects.api.ProjectService
import com.nurlan1507.task_manager_mobile.feature_projects.data.repository.ProjectRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.CreateProjectNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.CreateProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectsNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetProjectsUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.GetTodaysProjectUseCase
import com.nurlan1507.task_manager_mobile.feature_projects.domain.use_cases.ProjectUseCases
import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_tasks.api.TasksService
import com.nurlan1507.task_manager_mobile.feature_tasks.data.repository.TasksRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.CreateTaskNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.CreateTaskUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.DeleteTaskUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetDueTodayTasks
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetTasksNetworkUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.GetTasksUseCase
import com.nurlan1507.task_manager_mobile.feature_tasks.domain.use_cases.TasksUseCases
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthRemoteDataSource
import com.nurlan1507.task_manager_mobile.feature_users.api.AuthService
import com.nurlan1507.task_manager_mobile.feature_users.data.repository.UserRepositoryImpl
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.AddUserToLocalDb
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GetUserUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.GoogleSignInUseCase
import com.nurlan1507.task_manager_mobile.feature_users.domain.use_cases.UserUseCases
import com.nurlan1507.task_manager_mobile.restService.RestService
import com.nurlan1507.task_manager_mobile.room_database.TaskManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): TaskManagerDatabase {
        return Room.databaseBuilder(app.applicationContext, TaskManagerDatabase::class.java,
            TaskManagerDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRestService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RestService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(RestService.okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskService(retrofit: Retrofit):TasksService{
        return retrofit.create(TasksService::class.java)
    }
    @Provides
    @Singleton
    fun provideProjectService(retrofit: Retrofit): ProjectService {
        return retrofit.create(ProjectService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit):AuthService{
        return retrofit.create(AuthService::class.java)
    }




    @Provides
    @Singleton
    fun projectRepository(db:TaskManagerDatabase,projectRemoteDataSource: ProjectRemoteDataSource): ProjectRepositoryImpl {
        return ProjectRepositoryImpl(projectDao = db.projectDao(), projectRemoteDataSource = projectRemoteDataSource )
    }

    @Provides
    @Singleton
    fun taskRepository(tasksRemoteDataSource: TasksRemoteDataSource,db:TaskManagerDatabase): TasksRepositoryImpl {
        return TasksRepositoryImpl(taskDao = db.taskDao(), remoteDataSource = tasksRemoteDataSource)
    }
    @Provides
    @Singleton
    fun userRepository(authRemoteDataSource: AuthRemoteDataSource,db:TaskManagerDatabase): UserRepositoryImpl {
        return UserRepositoryImpl(dao = db.userDao(), remoteDataSource = authRemoteDataSource)
    }





    //RemoteDataSources
    @Provides
    @Singleton
    fun taskRemoteDataSource(tasksService: TasksService): TasksRemoteDataSource {
        return TasksRemoteDataSource(tasksService)
    }
    @Provides
    @Singleton
    fun authRemoteDataSource(authService: AuthService): AuthRemoteDataSource {
        return AuthRemoteDataSource(authService)
    }

    @Provides
    @Singleton
    fun projectRemoteDataSource(projectService: ProjectService): ProjectRemoteDataSource {
        return ProjectRemoteDataSource(projectService)
    }







    //useCases
    @Provides
    @Singleton
    fun userUseCases(userRepository: UserRepositoryImpl): UserUseCases {
        return UserUseCases(
            GetUserUseCase(userRepository),
            GoogleSignInUseCase(userRepository),
            AddUserToLocalDb(userRepository)
        )
    }
    @Provides
    @Singleton
    fun projectUseCases(projectRepository: ProjectRepositoryImpl): ProjectUseCases {
        return ProjectUseCases(
            GetProjectUseCase(projectRepository),
            CreateProjectUseCase(projectRepository),
            CreateProjectNetworkUseCase(projectRepository),
            GetProjectsNetworkUseCase(projectRepository),
            GetProjectsUseCase(projectRepository),
            GetTodaysProjectUseCase(projectRepository),
        )
    }
    @Provides
    @Singleton
    fun taskUseCases(taskRepository: TasksRepositoryImpl): TasksUseCases {
        return TasksUseCases(
            CreateTaskUseCase(taskRepository),
            GetTasksUseCase(taskRepository),
            GetDueTodayTasks(taskRepository),
            DeleteTaskUseCase(taskRepository),
            GetTasksNetworkUseCase(taskRepository),
            CreateTaskNetworkUseCase(taskRepository),
        )
    }

}
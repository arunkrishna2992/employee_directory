package com.white.employeeapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.white.employeeapp.data.local.AppDatabase
import com.white.employeeapp.data.local.EmployeeDao
import com.white.employeeapp.data.remote.EmployeeRemoteDataSource
import com.white.employeeapp.data.remote.EmployeeService
import com.white.employeeapp.data.repo.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://www.mocky.io/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService = retrofit.create(EmployeeService::class.java)

    @Singleton
    @Provides
    fun provideEmployeeRemoteDataSource(employeeService: EmployeeService) = EmployeeRemoteDataSource(employeeService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideEmployeeDao(db: AppDatabase) = db.employeesDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: EmployeeRemoteDataSource,
                          localDataSource: EmployeeDao) =
        EmployeeRepository(remoteDataSource, localDataSource)
}
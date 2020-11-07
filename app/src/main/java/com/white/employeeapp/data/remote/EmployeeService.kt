package com.white.employeeapp.data.remote

import com.white.employeeapp.data.entities.EmployeeInfo
import com.white.employeeapp.data.entities.EmployeeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeService {
    @GET("5d565297300000680030a986")
    suspend fun getAllEmployees() : Response<List<EmployeeInfo>>

}
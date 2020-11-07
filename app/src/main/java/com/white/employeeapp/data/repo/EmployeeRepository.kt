package com.white.employeeapp.data.repo

import androidx.lifecycle.LiveData
import com.white.employeeapp.data.entities.EmployeeInfo
import com.white.employeeapp.data.local.EmployeeDao
import com.white.employeeapp.data.remote.EmployeeRemoteDataSource
import com.white.employeeapp.utils.performGetOperation
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val remoteDataSource: EmployeeRemoteDataSource,
    private val localDataSource: EmployeeDao
) {

    fun getEmployee(id:Int): LiveData<EmployeeInfo> {
        return localDataSource.getEmployee(id)
    }

    fun getEmployees() = performGetOperation(
        databaseQuery = { localDataSource.getAllEmployees() },
        networkCall = { remoteDataSource.getEmployees() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}
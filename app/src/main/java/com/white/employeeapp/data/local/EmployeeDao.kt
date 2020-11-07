package com.white.employeeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.white.employeeapp.data.entities.EmployeeInfo

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees() : LiveData<List<EmployeeInfo>>

    @Query("SELECT * FROM employees WHERE id = :id")
    fun getEmployee(id: Int): LiveData<EmployeeInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<EmployeeInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employees: EmployeeInfo)


}

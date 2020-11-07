package com.white.employeeapp.ui.employeeinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.white.employeeapp.data.entities.EmployeeInfo
import com.white.employeeapp.data.repo.EmployeeRepository
import kotlin.properties.Delegates

class EmployeeInfoViewModel @ViewModelInject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    fun start(it: Int) {
        employeeId = it
    }

    val empoyees = repository.getEmployees()

    var employeeId = 0

    val employee = repository.getEmployee(employeeId)

}
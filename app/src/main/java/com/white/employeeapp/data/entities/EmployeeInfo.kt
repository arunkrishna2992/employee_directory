package com.white.employeeapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

@Entity(tableName = "employees")
data class EmployeeInfo (

	@PrimaryKey
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("username") val username : String,
	@SerializedName("email") val email : String?,
	@SerializedName("profile_image") val profile_image : String?,
	//@SerializedName("address") val address : Address,
	@Nullable@SerializedName("phone") val phone : String?,
	@SerializedName("website") val website : String?
	//@SerializedName("company") val company : Company
)
package com.white.employeeapp.data.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company")
data class Company (

	@SerializedName("name") val name : String,
	@SerializedName("catchPhrase") val catchPhrase : String,
	@SerializedName("bs") val bs : String
)
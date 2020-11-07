package com.white.employeeapp.data.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "address")
data class Address (

	@SerializedName("street") val street : String,
	@SerializedName("suite") val suite : String,
	@SerializedName("city") val city : String,
	@SerializedName("zipcode") val zipcode : String,
	@SerializedName("geo") val geo : Geo
)
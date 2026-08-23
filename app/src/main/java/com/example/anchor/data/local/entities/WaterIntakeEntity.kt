package com.example.anchor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_intake")
data class WaterIntakeEntity(
    @PrimaryKey
    val date: Long, // Start of day in millis (ensures one entry per day)
    val amountMl: Int,
    val goalMl: Int = 2000 // Default goal
)

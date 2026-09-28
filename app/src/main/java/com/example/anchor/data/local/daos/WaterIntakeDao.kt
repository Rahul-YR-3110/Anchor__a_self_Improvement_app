package com.example.anchor.data.local.daos

import androidx.room.*
import com.example.anchor.data.local.entities.WaterIntakeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterIntakeDao {
    @Query("SELECT * FROM water_intake WHERE date = :date")
    fun getWaterIntakeForDate(date: Long): Flow<WaterIntakeEntity?>
    @Upsert
    suspend fun upsertWaterIntake(waterIntake: WaterIntakeEntity)
}

package com.example.anchor.data.local.daos

import androidx.room.*
import com.example.anchor.data.local.entities.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("""
        UPDATE habits 
        SET streak = streak + 1 
        WHERE id = :habitId
    """)
    suspend fun incrementStreak(habitId: String)
}

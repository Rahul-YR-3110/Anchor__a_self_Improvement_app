package com.example.anchor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey 
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val streak: Int = 0
)

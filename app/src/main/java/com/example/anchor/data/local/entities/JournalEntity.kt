package com.example.anchor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "journals")
data class JournalEntity(
    @PrimaryKey 
    val id: String = UUID.randomUUID().toString(),
    val notes: String,
    val timestamp: LocalDateTime,
    val mood: Mood
)

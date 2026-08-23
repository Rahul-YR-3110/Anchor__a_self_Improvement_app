package com.example.anchor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "journals")
data class JournalEntity(
    @PrimaryKey 
    val id: String = UUID.randomUUID().toString(),
    val notes: String,
    val timestamp: Long, // We will store LocalDateTime as epoch milliseconds
    val mood: String     // We will store the Mood enum as a String (e.g., "SUNNY")
)

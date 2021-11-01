package com.example.mydatabaselibrary.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogNotes(
    val date: String,
    @PrimaryKey(autoGenerate = false)
    val time: String,
    val type: String,
    val className: String,
    val functionName: String,
    val lineNumber: String,
)

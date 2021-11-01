package com.example.mydatabaselibrary.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydatabaselibrary.db.entities.LogNotes


@Dao
interface LogDao {

    // Inserting Log Notes into the Database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: LogNotes)

    // Query for Fetching All Log Notes
    @Query("SELECT * FROM LogNotes")
    suspend fun getAllLogs(): List<LogNotes>
}
package com.example.mydatabaselibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydatabaselibrary.db.entities.LogNotes

@Database(entities = [LogNotes::class], version = 1)
abstract class LogNoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): LogDao

    companion object {
        @Volatile
        private var instance: LogNoteDatabase? = null

        @Synchronized
        fun getLogNoteDatabase(ctx: Context): LogNoteDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, LogNoteDatabase::class.java,
                    "log_note_database.db")
                    .build()

            return instance!!

        }

    }
}
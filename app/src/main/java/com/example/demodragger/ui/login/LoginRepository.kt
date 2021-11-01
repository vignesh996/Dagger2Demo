package com.example.demodragger.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demodragger.db.AppDataBase
import com.example.demodragger.db.entities.User
import com.example.demodragger.network.ApiStories
import com.example.mydatabaselibrary.db.LogNoteDatabase
import com.example.mydatabaselibrary.db.entities.LogNotes
import javax.inject.Inject
import javax.inject.Named

class LoginRepository @Inject constructor(@Named("clover-sandBox")private val apiStories: ApiStories,
                                          application: Application){

    // Database Instantiation
    private var dataBase =AppDataBase.getDataBase(application)

    //LogNoteDatabase Instantiation
    private var logNoteDatabase =  LogNoteDatabase.getLogNoteDatabase(application)

    // User Authentication communicate to the database
    suspend fun userAuth(name: String, password: String) : User {
      return dataBase.getUserDao().getUser(name, password)
    }

    // Inserting Log Data and communicate to the database
    suspend fun insertLog(log: LogNotes) {
        return logNoteDatabase.getNoteDao().insert(log)
    }

    // Fetching All Log Data and communicate to the database
    suspend fun getAllLog(): List<LogNotes>{
        return logNoteDatabase.getNoteDao().getAllLogs()
    }

}
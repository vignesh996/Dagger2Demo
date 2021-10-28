package com.example.demodragger.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demodragger.db.AppDataBase
import com.example.demodragger.db.entities.User
import com.example.demodragger.network.ApiStories
import javax.inject.Inject
import javax.inject.Named

class LoginRepository @Inject constructor(@Named("clover-sandBox")private val apiStories: ApiStories,
                                          application: Application){

    // Database Instantiation
    private var dataBase =AppDataBase.getDataBase(application)

    // User Authentication communicate to a database
    suspend fun userAuth(name: String, password: String) : User {
      return dataBase.getUserDao().getUser(name, password)
    }
}
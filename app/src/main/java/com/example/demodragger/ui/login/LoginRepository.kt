package com.example.demodragger.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demodragger.db.AppDataBase
import com.example.demodragger.db.entities.User
import com.example.demodragger.network.ApiStories
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiStories: ApiStories, application: Application){

    private var dataBase =AppDataBase.getDataBase(application)

    suspend fun userAuth(name: String, password: String) : User {
      return dataBase.getUserDao().getUser(name, password)
    }
}
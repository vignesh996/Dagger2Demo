package com.example.demodragger.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.demodragger.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll() : LiveData<List<User>>

    @Insert
    fun insertAll(user: List<User>)

    @Query("SELECT * FROM user WHERE name = :name AND password = :password ")
    suspend fun getUser(name : String, password : String): User

}
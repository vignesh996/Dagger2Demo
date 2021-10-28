package com.example.demodragger.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.demodragger.db.entities.User

@Dao
interface UserDao {

    // Query for Fetching All Users
    @Query("SELECT * FROM user")
    fun getAll() : LiveData<List<User>>

    // Query for Inserting List of User
    @Insert
    fun insertAll(user: List<User>)

    // Query for User Authentication
    @Query("SELECT * FROM user WHERE name = :name AND password = :password ")
    suspend fun getUser(name : String, password : String): User

}
package com.example.demodragger.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var password: String){



}
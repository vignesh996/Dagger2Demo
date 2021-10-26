package com.example.demodragger.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demodragger.db.entities.User
import com.example.demodragger.helper.ioThread


@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "user_database"
                ).addCallback(object :Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getDataBase(context).getUserDao().insertAll(USER_LIST)
                        }
                    }
                }).build()
                INSTANCE= instance
                return instance
            }
        }

        val USER_LIST = listOf(User(1, "vignesh", "vignesh"), User(2, "jaya", "jaya"))
    }
}
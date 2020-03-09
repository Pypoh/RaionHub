package com.example.raionhub.model

import androidx.room.RoomDatabase

abstract class UserDatabase : RoomDatabase(){

    abstract  fun userDao() : UserDao

    abstract fun
}
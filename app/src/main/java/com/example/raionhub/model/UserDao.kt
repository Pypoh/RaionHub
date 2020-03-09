package com.example.raionhub.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // Metdod insert
    @Insert
    fun insert(user : User)

    // Method update
    @Update
    fun Update(user : User)

    // Method delete
    @Query("delete from user where user_id = :id")
    fun deleteById(id: Int)

    @Delete
    fun Delete(user : User)

    // Method get all user
    @Query("select * from user")
    fun getAllNotes(): LiveData<List<User>>
}
package com.trinitydigital.roomsecond.data

import androidx.room.*

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: LoginModel)

    @Update
    fun updateUser(user: LoginModel)

    @Query("SELECT * FROM LoginModel WHERE login = :loginText AND password = :passwordText")
    fun getUser(loginText: String, passwordText: String): LoginModel

    @Query("SELECT * FROM LoginModel")
    fun getAllUsers(): List<LoginModel>

    @Query("DELETE FROM LoginModel")
    fun deleteAll()

    @Transaction
    fun addUserAndDeleteOld(user: LoginModel) {
        deleteAll()
        addUser(user)
    }
}
package com.trinitydigital.roomsecond.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDB : RoomDatabase() {

    abstract fun getAuthDao(): AuthDao
}
package com.trinitydigital.roomsecond

import android.app.Application
import androidx.room.Room
import com.trinitydigital.roomsecond.data.LoginDB

class MyApp : Application() {

    private var db: LoginDB? = null

    override fun onCreate() {
        super.onCreate()
        app = this

        db = Room.databaseBuilder(applicationContext, LoginDB::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build()
    }

    fun getDb() = db

    companion object {
        var app: MyApp? = null
        private const val DB_NAME = "MY_DB"
    }
}
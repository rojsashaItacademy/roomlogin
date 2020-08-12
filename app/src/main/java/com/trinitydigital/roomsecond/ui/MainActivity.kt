package com.trinitydigital.roomsecond.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trinitydigital.roomsecond.MyApp
import com.trinitydigital.roomsecond.R
import com.trinitydigital.roomsecond.data.AuthDao
import com.trinitydigital.roomsecond.data.LoginModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etLogin
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_sign_in.*

class MainActivity : AppCompatActivity() {

    private var authDao: AuthDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authDao = MyApp.app?.getDb()?.getAuthDao()
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        val result = authDao?.getAllUsers()?.first()
        etLogin.setText(result?.login)
        etPassword.setText(result?.password)
    }

    private fun setupListeners() {
        btnUpdate.setOnClickListener {
            authDao?.updateUser(
                LoginModel(
                    login = etLogin.text.toString(),
                    password = etPassword.text.toString()
                )
            )
        }

        btnLogout.setOnClickListener {
            authDao?.deleteAll()
            val preference = getSharedPreferences("adasdasdasd", Context.MODE_PRIVATE)
            preference.edit().putBoolean("autoEnter", false).apply()
        }
    }
}
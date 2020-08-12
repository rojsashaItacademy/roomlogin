package com.trinitydigital.roomsecond.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trinitydigital.roomsecond.MyApp
import com.trinitydigital.roomsecond.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.etLogin
import kotlinx.android.synthetic.main.activity_sign_in.etPassword

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preference = getSharedPreferences("adasdasdasd", Context.MODE_PRIVATE)

        if (preference.getBoolean("autoEnter", false)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_sign_in)
        setupListeners(preference)
    }

    private fun setupListeners(preference: SharedPreferences) {
        tvRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        btnSignIn.setOnClickListener {
            if (isEtNotEmpty()) {
                val result =
                    MyApp.app?.getDb()?.getAuthDao()?.getUser(
                        getLogin(),
                        getPassword()
                    )

                if (result != null) {
                    preference.edit().putBoolean("autoEnter", autoEnter.isChecked).apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.sign_up_error), Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, getString(R.string.sign_up_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEtNotEmpty(): Boolean {
        val resultLogin = getLogin().length > 6
        val resultPassword = getPassword().length > 7

        return resultLogin && resultPassword
    }

    private fun getLogin() = etLogin.text.toString()
    private fun getPassword() = etPassword.text.toString()
}
package com.trinitydigital.roomsecond.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trinitydigital.roomsecond.MyApp
import com.trinitydigital.roomsecond.R
import com.trinitydigital.roomsecond.data.LoginModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupListeners()
    }

    private fun setupListeners() {
        btnEnter.setOnClickListener {
            if (isEtNotEmpty() && isPasswordsSimilar()) {
                MyApp.app?.getDb()?.getAuthDao()?.addUserAndDeleteOld(
                    LoginModel(
                        id = 1,
                        login = etLogin.text.toString(),
                        password = etPassword.text.toString()
                    )
                )

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.sign_up_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPasswordsSimilar() =
        etPassword.text.toString() == etPasswordRepeat.text.toString()

    private fun isEtNotEmpty(): Boolean {
        val resultLogin = etLogin.text.toString().length > 6
        val resultPassword = etPassword.text.toString().length > 7
        val resultPasswordRepeat = etPasswordRepeat.text.toString().length > 7

        return resultLogin && resultPassword && resultPasswordRepeat
    }
}
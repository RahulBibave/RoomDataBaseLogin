package com.rahul.roomdatabaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        btn_signup.setOnClickListener {
            startActivity(Intent(this,ActivitySignup::class.java))
        }
    }
}

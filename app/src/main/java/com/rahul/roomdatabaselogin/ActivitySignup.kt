package com.rahul.roomdatabaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.rahul.roomdatabaselogin.db.MyDatabase
import com.rahul.roomdatabaselogin.db.Registration
import java.util.regex.Pattern


class ActivitySignup : AppCompatActivity(), View.OnClickListener {


    var et_name: AppCompatEditText?=null;
    var et_email: AppCompatEditText?=null;
    var et_mobile: AppCompatEditText?=null;
    var et_password: AppCompatEditText?=null;
    var checkbox: CheckBox?=null;
    var signup: ImageButton?=null;
    var signin: Button?=null;
    var emailPatter:Pattern?=null;
    var mobilePatter:Pattern?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initUI()
    }
    fun initUI()
    {
        et_name=findViewById(R.id.et_name)
        et_email=findViewById(R.id.et_email)
        et_mobile=findViewById(R.id.et_mobile)
        et_password=findViewById(R.id.et_password)
        checkbox=findViewById(R.id.checkbox)
        signup=findViewById(R.id.signup)
        signin=findViewById(R.id.signin)
        signup?.setOnClickListener(this)
        signin?.setOnClickListener(this)

        emailPatter= Patterns.EMAIL_ADDRESS;
        mobilePatter= Patterns.PHONE;
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.signup->{

                var name=et_name?.text.toString().trim()
                var email=et_email?.text.toString().trim()
                var mobile=et_mobile?.text.toString().trim()
                var pass=et_password?.text.toString().trim()
                var registration:Registration= Registration(name,email,pass,mobile)
                if(name.length<=0)
                {
                    Toast.makeText(applicationContext,"Please Enter Name",Toast.LENGTH_SHORT).show()
                    return;
                }
                if(email.length<=0)
                {
                    Toast.makeText(applicationContext,"Please Enter Email",Toast.LENGTH_SHORT).show()
                    return;
                }
                if(mobile.length<=0)
                {
                    Toast.makeText(applicationContext,"Please Enter Mobile",Toast.LENGTH_SHORT).show()
                    return;
                }
                if(pass.length<=0)
                {
                    Toast.makeText(applicationContext,"Please Enter Password",Toast.LENGTH_SHORT).show()
                    return;
                }
                if(!(emailPatter?.matcher(email)?.find())!!)
                {
                    Toast.makeText(applicationContext,"Please Enter valid email",Toast.LENGTH_SHORT).show()
                    return;
                }
                if(!(mobilePatter?.matcher(mobile)?.find())!!)
                {
                    Toast.makeText(applicationContext,"Please Enter valid mobile number", Toast.LENGTH_SHORT).show()
                    return;
                }
                var list= MyDatabase.getInstance(applicationContext).registrationDAO().checkLogin(email,pass) as List;
                if(list.size<=0)
                {
                    MyDatabase.getInstance(applicationContext).registrationDAO().insert(registration);

                    callSignIn()
                    Toast.makeText(applicationContext,"user registered successfuly",Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(applicationContext,"user already exist with this email Id",Toast.LENGTH_SHORT).show()
                }


            }
            R.id.signin->{

                callSignIn()
            }
        }
    }

    override fun onBackPressed() {
        callSignIn()
    }

    private fun callSignIn()
    {

        var intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
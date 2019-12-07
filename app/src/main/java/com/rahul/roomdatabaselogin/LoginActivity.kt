package com.rahul.roomdatabaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.rahul.roomdatabaselogin.db.MyDatabase

class LoginActivity : AppCompatActivity() , View.OnClickListener {


    var toolbar: Toolbar?=null;
    var email: AppCompatEditText?=null;
    var password: AppCompatEditText?=null;
    var checkbox: CheckBox?=null;
    var signin: ImageButton?=null;
    var signup: Button?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
    }

    fun initUI()
    {
        toolbar=findViewById(R.id.toolbar);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        checkbox=findViewById(R.id.checkbox);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        signin?.setOnClickListener(this);
        signup?.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v?.id)
        {
            R.id.signup ->{
                var intent=Intent(applicationContext,ActivitySignup::class.java)
                startActivity(intent)
                finish()
            }
            R.id.signin ->{
                var emailStr=email?.text.toString().trim();
                var passStr=password?.text.toString().trim();
                var list= MyDatabase.getInstance(applicationContext).registrationDAO().checkLogin(emailStr,passStr) ;
                if(list.size<=0)
                {

                    Toast.makeText(applicationContext,"Invalid Credentials", Toast.LENGTH_LONG).show()
                    return;
                }


                var intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.v("Length ","Length" +list.size)
            }
        }
    }
}

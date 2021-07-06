package com.example.library

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val skip:AppCompatButton = findViewById(R.id.skip)
        skip.setOnClickListener {
            val intent = Intent(this,DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
        val registerlogin = findViewById<TextView>(R.id.registerlogin)
        val forgotpassword  = findViewById<TextView>(R.id.forgotpassword)
        registerlogin.setOnClickListener{
            val intent = Intent(this , RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        forgotpassword.setOnClickListener {
            val intent = Intent(this,ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }

        val emaillogin = findViewById<TextInputLayout>(R.id.email)
        val password=findViewById<TextInputLayout>(R.id.password)
        val login = findViewById<AppCompatButton>(R.id.login)
        login.setOnClickListener{
            val emailauth :String = emaillogin.editText!!.text.toString()
            val passauth:String = password.editText!!.text.toString()
            if (TextUtils.isEmpty(emailauth) || TextUtils.isEmpty(passauth)){
                val snackbar = Snackbar.make(it, "Please Fill all the field", Snackbar.LENGTH_LONG)
                val sbView: View = snackbar.view
                sbView.setBackgroundColor(resources.getColor(R.color.design_default_color_error))
                snackbar.show()
            }else{
                auth = Firebase.auth
                auth.signInWithEmailAndPassword(emailauth, passauth).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,DashboardActivity::class.java)
                        startActivity(intent)
                        finish()


                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })

            }



        }



    }


}
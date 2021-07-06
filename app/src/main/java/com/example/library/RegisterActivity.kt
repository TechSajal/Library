package com.example.library

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val UPDATEPROFILE:String ="profileCompleted"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        val lastname = findViewById<TextInputLayout>(R.id.lastname)
        val emailid = findViewById<TextInputLayout>(R.id.emailid)
        val password = findViewById<TextInputLayout>(R.id.passwordregister)
        val confirmpassword = findViewById<TextInputLayout>(R.id.confirmpassword)
        val checkbox = findViewById<CheckBox>(R.id.checkBox)
        val firstname = findViewById<TextInputLayout>(R.id.firstname)
        val register = findViewById<Button>(R.id.register)
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        register.setOnClickListener {

            val fname: String = firstname.editText!!.text.toString()
            val lname: String = lastname.editText!!.text.toString()
            val eid: String = emailid.editText!!.text.toString().trim(){it <= ' ' }
            val pas: String = password.editText?.text.toString().trim(){it <= ' ' }
            val cpas: String = confirmpassword.editText!!.text.toString()

            if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname) || TextUtils.isEmpty(eid) || TextUtils.isEmpty(pas) || TextUtils.isEmpty(cpas)|| checkbox.isChecked.not() ) {
                val snackbar = Snackbar.make(it, "Please Fill all the field", Snackbar.LENGTH_LONG)
                val sbView: View = snackbar.view
                sbView.setBackgroundColor(resources.getColor(R.color.design_default_color_error))
                snackbar.show()
            } else {
                auth = Firebase.auth
                auth.createUserWithEmailAndPassword(eid, pas)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val user = User(
                                firebaseUser.uid,
                                firstname.editText!!.text.toString().trim{ it <=  ' ' },
                                lastname.editText!!.text.toString().trim{ it <=  ' ' },
                                emailid.editText!!.text.toString().trim{ it <=  ' ' },
                            )
                            FirestoreClass().registeruser(this,user)
                            val snackbar = Snackbar.make(it, "Registration Successful", Snackbar.LENGTH_LONG)
                            val sbView: View = snackbar.view
                            sbView.setBackgroundColor(resources.getColor(R.color.colorThemeOrange))
                            snackbar.show()
                            val userHashMap = HashMap<String, Any>()
                            userHashMap[UPDATEPROFILE] = 1
                            FirestoreClass().updateUserProfile(this, userHashMap)
                            val intent = Intent(this,DashboardActivity::class.java)
                                startActivity(intent)
                                 finish()
                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                        }
                    }

            }


        }




    }

}
package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val submit = findViewById<Button>(R.id.submit)
        val et_email = findViewById<TextInputLayout>(R.id.emailfp)
        val backfp = findViewById<ImageView>(R.id.backfp)

        backfp.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        submit.setOnClickListener {
            val email: String = et_email.editText!!.text.toString().trim { it <= ' ' }

            if (email.isEmpty()){
                val snackbar = Snackbar.make(it,"Please fill Email-id", Snackbar.LENGTH_LONG)
                val sbView: View = snackbar.view
                sbView.setBackgroundColor(resources.getColor(R.color.design_default_color_error))
                snackbar.show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,"Email sent successfully to reset your password", Toast.LENGTH_LONG
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(this,"Operation Failed either you have enter wrong email-id ",
                                Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
            }


        }
    }
}
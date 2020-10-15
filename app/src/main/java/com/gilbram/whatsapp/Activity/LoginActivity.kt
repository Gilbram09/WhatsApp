package com.gilbram.whatsapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.gilbram.whatsapp.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_signup
import kotlinx.android.synthetic.main.activity_login.edt_password
import kotlinx.android.synthetic.main.activity_login.progress_layout
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser?.uid
        if (user != null) {
            val intent = Intent(this,MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTextChangedListener(edt_email, til_password_log)
        setTextChangedListener(edt_password, til_password_log)
        progress_layout.setOnTouchListener { v, event -> true }


        btn_signup.setOnClickListener {
            onLogin()
        }

        txt_signup.setOnClickListener {
            onSignup()
        }


    }

    private fun setTextChangedListener(edt: EditText, til: TextInputLayout) {
        edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            // ketika editText diubah memastikan TextInputLayout tidak menunjukkan pesan error
            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                til.isErrorEnabled = false
            }
        })
    }


    private fun onLogin() {
        var proceed = true
        if (edt_email.text.isNullOrEmpty()) {
            til_email_log.error = "Required Password"
            til_email_log.isErrorEnabled = true
            proceed = false
        }
        if (edt_password.text.isNullOrEmpty()) {
            til_password_log.error = "Required Password"
            til_password_log.isErrorEnabled = true
            proceed = false
        }
        if (proceed) {
            progress_layout.visibility = View.VISIBLE
            firebaseAuth.signInWithEmailAndPassword(
                edt_email.text.toString(),
                edt_password.text.toString()
            )

                .addOnCompleteListener { task ->
                    progress_layout.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "LOGIN ERROR${task?.exception?.localizedMessage}", Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    progress_layout.visibility = View.GONE
                    e.printStackTrace()
                }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }
    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }
    private fun onSignup() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }
}
package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.alexnassif.mobile.tennisbro.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        getStartedButton.setOnClickListener {
            val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
            startActivity(createAccountIntent)
        }

        loginBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }


    }

    override fun onStart() {
        super.onStart()


    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

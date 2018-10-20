package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.alexnassif.mobile.tennisbro.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()
    }

    fun createAccountBtnClicked(view: View){
        createAccountSpinner.visibility = View.VISIBLE

        if(!TextUtils.isEmpty(createEmailText.text.toString()) && !TextUtils.isEmpty(createPasswordText.text.toString())) {

            mAuth.createUserWithEmailAndPassword(createEmailText.text.toString(), createPasswordText.text.toString()).addOnCompleteListener {task ->

                if(task.isSuccessful){
                    createAccountSpinner.visibility = View.INVISIBLE
                    Toast.makeText(this, "account creation successfull", Toast.LENGTH_LONG).show()
                    val user = mAuth.currentUser
                    val intent = Intent(this, LeagueActivity::class.java)
                    intent.putExtra("username", usernameText.text.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "account creation failed", Toast.LENGTH_LONG).show()
                }


            }
        }


    }
}

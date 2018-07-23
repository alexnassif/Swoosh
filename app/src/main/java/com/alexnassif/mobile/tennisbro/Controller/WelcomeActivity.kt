package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alexnassif.mobile.tennisbro.LocatePlayerMapsActivity

import com.alexnassif.mobile.tennisbro.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        getStartedButton.setOnClickListener {
            val leagueIntent = Intent(this, LeagueActivity::class.java)
            startActivity(leagueIntent)
        }

        loginBtn.setOnClickListener{
            val mapIntent = Intent(this, LocatePlayerMapsActivity::class.java)
            startActivity(mapIntent)
        }
    }
}

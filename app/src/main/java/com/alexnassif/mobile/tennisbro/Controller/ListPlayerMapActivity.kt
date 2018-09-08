package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alexnassif.mobile.tennisbro.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list_player.*

class ListPlayerMapActivity : AppCompatActivity() {

    private lateinit var mapFragment: PlayerListMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_player)

        mapFragment = PlayerListMapFragment.newInstance()


        supportFragmentManager
                .beginTransaction()
                .replace(R.id.map_frame, mapFragment)
                .commit()


        listBtnToolbar.setOnClickListener {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map_frame, PlayerListFragment())
                    .commit()
        }

        sign_out.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

    }

}

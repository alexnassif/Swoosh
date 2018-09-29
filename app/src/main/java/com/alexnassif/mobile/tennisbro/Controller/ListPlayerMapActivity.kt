package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.alexnassif.mobile.tennisbro.R
import com.alexnassif.mobile.tennisbro.R.id.sign_out
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list_player.*

class ListPlayerMapActivity : AppCompatActivity() {

    private lateinit var mapFragment: PlayerListMapFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_player)

        mapFragment = PlayerListMapFragment.newInstance()
        setSupportActionBar(findViewById(R.id.map_toolbar))

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.map_frame, mapFragment)
                .commit()




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.map_view_type -> {


            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map_frame, PlayerListFragment())
                    .commit()



            true
        }

        R.id.sign_out -> {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}

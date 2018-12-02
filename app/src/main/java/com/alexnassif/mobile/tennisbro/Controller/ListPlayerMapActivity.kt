package com.alexnassif.mobile.tennisbro.Controller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import com.alexnassif.mobile.tennisbro.R
import com.alexnassif.mobile.tennisbro.R.id.sign_out
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list_player.*
import kotlinx.android.synthetic.main.activity_welcome.*

class ListPlayerMapActivity : AppCompatActivity() {

    private lateinit var mapFragment: PlayerListMapFragment
    private lateinit var listFragment: PlayerListFragment
    private lateinit var welcomeFragment: WelcomeFragment
    private var flag: Boolean = true
    private lateinit var mAuth: FirebaseAuth
    private lateinit var welcomeIntent: Intent
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_player)

        mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null) {
            toolbar = findViewById(R.id.map_toolbar)
            toolbar.visibility = View.VISIBLE
            mapFragment = PlayerListMapFragment.newInstance()
            listFragment = PlayerListFragment()
            setSupportActionBar(toolbar)

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map_frame, mapFragment)
                    .commit()
        }else{
            welcomeFragment = WelcomeFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map_frame, welcomeFragment)
                    .commit()

        }




    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.map_view_type -> {

            if(flag){

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.map_frame, listFragment)
                        .commit()

                item.setIcon(R.drawable.profiledefault)

            }else {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.map_frame, mapFragment)
                        .commit()

                item.setIcon(R.drawable.baseline_list_white_18dp)
            }
            flag = !flag
            true
        }

        R.id.sign_out -> {
            FirebaseAuth.getInstance().signOut()
            val restartIntent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
            restartIntent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(restartIntent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val LOGIN_REQUEST = 1
    }
}

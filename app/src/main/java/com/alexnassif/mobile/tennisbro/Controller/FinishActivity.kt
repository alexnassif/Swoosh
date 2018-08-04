package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R

import com.alexnassif.mobile.tennisbro.Utilities.EXTRA_PLAYER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeagueText.text = "creating user for a ${player.league} ${player.skill} in ${player.location}..."
        progressBar.visibility = View.VISIBLE
        var database = FirebaseDatabase.getInstance()
        val createUserRef = database.getReference("users")
        createUserRef.child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(player, object : DatabaseReference.CompletionListener {

            override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                if (p0 == null) {
                    progressBar.visibility = View.INVISIBLE
                    searchLeagueText.text = "user created"
                }else{
                    searchLeagueText.text = "error"
                }
            }

        })

    }
}

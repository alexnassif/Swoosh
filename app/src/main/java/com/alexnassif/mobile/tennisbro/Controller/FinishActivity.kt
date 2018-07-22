package com.alexnassif.mobile.tennisbro.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R

import com.alexnassif.mobile.tennisbro.Utilities.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeagueText.text = "Looking for a ${player.league} ${player.skill} league near you..."
    }
}

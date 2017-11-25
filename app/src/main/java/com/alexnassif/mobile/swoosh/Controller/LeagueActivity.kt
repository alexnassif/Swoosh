package com.alexnassif.mobile.swoosh.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.alexnassif.mobile.swoosh.Model.Player
import com.alexnassif.mobile.swoosh.R
import com.alexnassif.mobile.swoosh.Utilities.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {


    var player = Player("", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_PLAYER, player)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null){
            player = savedInstanceState.getParcelable(EXTRA_PLAYER)
        }
    }

    fun leagueNextClicked(view: View){

        if(player.league != "") {
            val skillActivity = Intent(this, SkillActivity::class.java)
            skillActivity.putExtra(EXTRA_PLAYER, player)
            startActivity(skillActivity)
        } else {
            Toast.makeText(this, "No league selected", Toast.LENGTH_SHORT).show()
        }
    }

    fun mensOnClick(view: View){
        womensLeagueBtn.isChecked = false
        coedLeagueBtn.isChecked = false
        player.league = "mens"
    }

    fun womensOnClicked(view: View){
        mensLeagueBtn.isChecked = false
        coedLeagueBtn.isChecked = false
        player.league = "womens"
    }

    fun coedOnClicked (view: View){
        mensLeagueBtn.isChecked = false
        womensLeagueBtn.isChecked = false
        player.league = "coed"

    }
}

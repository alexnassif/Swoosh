package com.alexnassif.mobile.swoosh.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alexnassif.mobile.swoosh.Model.Player
import com.alexnassif.mobile.swoosh.R
import com.alexnassif.mobile.swoosh.Utilities.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_skill.*

class SkillActivity : AppCompatActivity() {

    lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)

        player = intent.getParcelableExtra(EXTRA_PLAYER)
    }

    fun onBeginnerClick(view: View){
        ballerSkillBtn.isChecked = false
        player.skill = "beginner"
    }

    fun onBallerClick(view: View){
        beginnerSkillBtn.isChecked = false
        player.skill = "baller"
    }

    fun onFinishBtn(view: View){

        if(player.skill != "") {
            val finishIntent = Intent(this, FinishActivity::class.java)
            finishIntent.putExtra(EXTRA_PLAYER, player)
            startActivity(finishIntent)
        }else{
            Toast.makeText(this, "Please select a skill level", Toast.LENGTH_SHORT).show()
        }
    }
}

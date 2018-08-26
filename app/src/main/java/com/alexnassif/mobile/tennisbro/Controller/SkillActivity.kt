package com.alexnassif.mobile.tennisbro.Controller

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alexnassif.mobile.tennisbro.LocatePlayerMapsActivity
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R

import com.alexnassif.mobile.tennisbro.Utilities.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_skill.*

class SkillActivity : AppCompatActivity() {

    lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)

        player = intent.getParcelableExtra(EXTRA_PLAYER)
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
    fun onBeginnerClick(view: View){
        moderateSkillBtn.isChecked = false
        advancedSkillBtn.isChecked = false
        player.skill = "beginner"
    }

    fun onModerateClick(view: View){
        beginnerSkillBtn.isChecked = false
        advancedSkillBtn.isChecked = false
        player.skill = "moderate"
    }
    fun onAdvancedClick(view: View){
        beginnerSkillBtn.isChecked = false
        moderateSkillBtn.isChecked = false
        player.skill = "moderate"
    }

    fun setPlayerLocationBtn(view: View){
        val finishIntent = Intent(this, LocatePlayerMapsActivity::class.java)
        startActivityForResult(finishIntent, PICK_LOCATION)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_LOCATION){

            if(resultCode == Activity.RESULT_OK){
                player.latitude = data!!.getStringExtra("latitude")
                player.longitude = data!!.getStringExtra("longitude")

            }
        }
    }
    companion object {
        private const val PICK_LOCATION = 1;
    }
}

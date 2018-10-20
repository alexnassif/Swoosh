package com.alexnassif.mobile.tennisbro.Adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R
import kotlinx.android.synthetic.main.player_row.view.*

class PlayerListAdapter(val context: Context, var users: List<Player>, val itemClick: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerListAdapter.CellHolder>(){
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CellHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_row, parent, false)
        return CellHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        holder.bindPlayer(users[position], context)
    }

    fun setList(list: List<Player>){
        users = list
        notifyDataSetChanged()
    }
    inner class CellHolder(view: View, val itemClick: (Player) -> Unit): RecyclerView.ViewHolder(view){
        val playerName = view.findViewById<TextView>(R.id.playerName)
        val playerSkill = view.findViewById<TextView>(R.id.playerSkill)

        fun bindPlayer(player: Player, context: Context){
            playerName?.text = player.name
            playerSkill?.text = player.skill

            itemView.setOnClickListener {
                itemClick(player)
            }
        }

    }
}
package com.alexnassif.mobile.tennisbro.Repository

import com.alexnassif.mobile.tennisbro.Model.Player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object DataService {

    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")

    fun getAllUsers(completion: (List<Player>) -> Unit){

        val listOfUsers = mutableListOf<Player>()
        usersRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(players: DataSnapshot) {
                val children = players!!.children

                children.forEach { x ->
                    val player = x.getValue(Player::class.java)
                    listOfUsers.add(player!!)

                }
                completion(listOfUsers)

            }


        })


    }





}
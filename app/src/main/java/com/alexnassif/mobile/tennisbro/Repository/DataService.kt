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
                    player!!.id = x.key.toString()



                    /**player.name = x.child("name").value.toString()
                    player.longitude = x.child("longitude").value as Double
                    player.latitude = x.child("latitude").value as Double
                    player.skill = x.child("skill").value.toString()
                    player.league = x.child("league").value.toString()
                    player.id = x.key.toString()**/
                    listOfUsers.add(player!!)

                }
                completion(listOfUsers)

            }


        })


    }





}
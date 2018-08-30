package com.alexnassif.mobile.tennisbro.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import com.alexnassif.mobile.tennisbro.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*

class ListPlayerMapActivity : AppCompatActivity() {

    lateinit var mapFragment: PlayerListMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_player)

        mapFragment = PlayerListMapFragment.newInstance()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.map_frame, mapFragment)
                .commit()

    }

}

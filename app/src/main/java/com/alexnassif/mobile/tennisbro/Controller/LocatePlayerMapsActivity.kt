package com.alexnassif.mobile.tennisbro.Controller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alexnassif.mobile.tennisbro.R

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.auth.FirebaseAuth

class LocatePlayerMapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locate_player_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnCameraIdleListener {

            latitude = mMap.cameraPosition.target.latitude
            longitude = mMap.cameraPosition.target.longitude
        }
        //setUpMap()
    }
    fun clickBall(view: View){
        FirebaseAuth.getInstance().signOut()
    }
    fun getLocationBtn(view: View){

        val intent = Intent();
        intent.putExtra("latitude", latitude)
        intent.putExtra("longitude", longitude)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    /*private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

    }*/
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1


    }
}


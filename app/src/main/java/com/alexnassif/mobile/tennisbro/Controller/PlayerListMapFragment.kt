package com.alexnassif.mobile.tennisbro.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerListMapFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var mMapView: MapView
    private lateinit var currentPlayer: Player


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPlayer = Player()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_list_player_map, container, false)

        mMapView = rootView.findViewById(R.id.map)
        mMapView.onCreate(savedInstanceState)
        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity.applicationContext)
        }catch (e: Exception){
            e.printStackTrace()
        }

        mMapView.getMapAsync {
            mMap = it

            // Add a marker in Sydney and move the camera

            var database = FirebaseDatabase.getInstance()
            val createUserRef = database.getReference("users")

            createUserRef.addValueEventListener(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val children = p0!!.children

                    children.forEach { x ->

                        if(x.key.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {

                            currentPlayer.league = x.child("league").value.toString()
                            currentPlayer.skill = x.child("skill").toString()
                            currentPlayer.latitude = x.child("latitude").value as Double
                            currentPlayer.longitude = x.child("longitude").value as Double
                            currentPlayer.name = x.child("name").value.toString()
                        }

                        val latitude = x.child("latitude").value
                        val longitude = x.child("longitude").value

                        val player = LatLng(latitude as Double, longitude as Double)
                        mMap.addMarker(MarkerOptions().position(player).title(x.key))
                    }

                }


            })


            val me = LatLng(currentPlayer.latitude, currentPlayer.longitude)
            mMap.addMarker(MarkerOptions().position(me).title("My Location"))
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 12.0f))



        }

        return rootView
    }

    companion object {

        fun newInstance(): PlayerListMapFragment {
            val fragment = PlayerListMapFragment()
            return fragment
        }
    }

}

package com.alexnassif.mobile.tennisbro.Controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.R
import com.alexnassif.mobile.tennisbro.ViewModel.UserViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class PlayerListMapFragment : Fragment() {


    private lateinit var mMap: GoogleMap
    private lateinit var mMapView: MapView
    private lateinit var currentPlayer: Player
    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPlayer = Player()

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_list_player_map, container, false)

        mMapView = rootView.findViewById(R.id.map)
        mMapView.onCreate(savedInstanceState)
        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        }catch (e: Exception){
            e.printStackTrace()
        }

        mMapView.getMapAsync {
            mMap = it

            // Add a marker in Sydney and move the camera

            viewModel.getUserList().observe(this, Observer {

                for(player in it) {

                    val location = LatLng(player.latitude, player.longitude)
                    mMap.addMarker(MarkerOptions().position(location).title(player.name))
                }
            })




            mMap.uiSettings.isZoomControlsEnabled = true

            mMap.setOnCameraIdleListener {
                val northEast = mMap.projection.visibleRegion.latLngBounds.northeast
                val southWest = mMap.projection.visibleRegion.latLngBounds.southwest

                /*val searchRef = database.getReference("users")

                searchRef.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {

                    }

                })*/

            }


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

package com.example.schoolmaplocation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schoolmaplocation.R
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.tools.Constants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_details.*

class Details : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var school: School

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        school = intent.getSerializableExtra(Constants.ACTIVITY_DETAILS) as School

        school_name_desc.text = school.name
        school_address_desc.text = school.formatted_address
        school_rating_desc.text = school.rating.toString()
        school_distance_desc.text = school.distanceTo.toString()


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_desc) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!

        val zoom = CameraUpdateFactory.zoomTo(15F)
        val storeLocation = LatLng(school.location_lat.toDouble(), school.location_long.toDouble())
        mMap.addMarker(MarkerOptions().position(storeLocation).title(school.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(storeLocation))
        mMap.animateCamera(zoom)
    }
}

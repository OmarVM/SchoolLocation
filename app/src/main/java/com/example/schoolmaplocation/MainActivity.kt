package com.example.schoolmaplocation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.tools.Constants
import com.example.schoolmaplocation.views.*
import com.example.schoolmaplocation.views.adapters.AdapterListSchools
import com.example.schoolmaplocation.views.presenters.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CurrentLocationUI,  ListSchoolsUI, FilterDistanceUI, OnClickItemAdapter, OnMapReadyCallback {

    private lateinit var context: Context
    private lateinit var mMap: GoogleMap
    private lateinit var requestLocation: CurrentLocationPres
    private lateinit var requestListSchool: ListSchools
    private lateinit var requestSortList: RequestListSortImpl

    private lateinit var listSchools: ArrayList<School>
    private lateinit var mAdapter: AdapterListSchools

    private var mLocationLat: Double = 0.0
    private var mLocationLong: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = applicationContext

        requestPermissionLocation()
    }

    override fun userCurrentLocation(lat: Double, long: Double) {
        mLocationLat = lat
        mLocationLong = long
        localRequestList(lat, long)
    }

    fun requestPermissionLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            val listPermission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(this, listPermission, Constants.KEY_RESQUEST_PERMISSION_LOCATION)
        }else{
            requestLocation = RequestCurrentLocationImp(this, this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            Constants.KEY_RESQUEST_PERMISSION_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    requestLocation = RequestCurrentLocationImp(this, this)
                }else{
                    localRequestList(0.0, 0.0)
                }
            }
        }
    }

    override fun listInfo(listItems: List<School>, hasPermission: Boolean) {
        listSchools = listItems as ArrayList<School>

        if (hasPermission) {
            requestSortList = RequestListSortImpl(context, listSchools, this)
        }else{
            showListItems(listSchools)
        }
    }

    override fun listInfoError(messageError: String, typeError: Int) {
        Log.d("TAG", "Error -> $messageError")
    }

    override fun sortListItem(listItems: List<School>) {
        showListItems(listItems)
    }

    override fun onClickItem(item: School) {
        var intent = Intent(context, Details::class.java)
        intent.putExtra(Constants.ACTIVITY_DETAILS, item)
        startActivity(intent)

    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!

        if(listSchools.isNotEmpty()){
            val zoom = CameraUpdateFactory.zoomTo(10F)
            for (school in listSchools){
                val storeLocation = LatLng(school.location_lat.toDouble(), school.location_long.toDouble())
                mMap.addMarker(MarkerOptions().position(storeLocation).title(school.name))
            }

            var center = LatLng(19.432304, -99.178723)
            if(mLocationLat != 0.0 && mLocationLong != 0.0){
                center = LatLng(mLocationLat, mLocationLong)
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLng(center))
            mMap.animateCamera(zoom)
        }

    }

    private fun localRequestList(lat: Double, long: Double){
        requestListSchool = RequestListSchoolsImp( this, this, lat, long)
    }

    private fun showListItems(listItems: List<School>){
        rv_list_schools.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = AdapterListSchools(listSchools, this)
        rv_list_schools.adapter = mAdapter

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}

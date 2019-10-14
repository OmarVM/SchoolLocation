package com.example.schoolmaplocation.logic.interactors

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.example.schoolmaplocation.views.presenters.CurrentLocationPres

class GetCurrentLocation {

    private var context: Context
    private var presenter: CurrentLocationPres

    constructor(context: Context, presenter: CurrentLocationPres) {
        this.context = context
        this.presenter = presenter
    }


    @SuppressLint("MissingPermission")
    fun getCurrentLocation(){
        var locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled: Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (gpsEnabled){
            var currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (currentLocation != null){
                presenter.userCurrentLocation(currentLocation.latitude, currentLocation.longitude)
            }else{
                presenter.userCurrentLocation(0.0, 0.0)
            }
        }
    }
}
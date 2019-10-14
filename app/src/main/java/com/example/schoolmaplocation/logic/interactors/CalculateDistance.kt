package com.example.schoolmaplocation.logic.interactors

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.views.presenters.FilterByDistancePre

class CalculateDistance {

    private var context: Context
    private var presenter: FilterByDistancePre

    constructor(context: Context, presenter: FilterByDistancePre) {
        this.context = context
        this.presenter = presenter
    }

    @SuppressLint("MissingPermission")
    fun setDistace(listItems: List<School>){
        var locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled: Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (gpsEnabled){
            var currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            for (school in listItems){
                var schoolLocation = Location("schoolLocation")
                schoolLocation.latitude = school.location_lat.toDouble()
                schoolLocation.longitude = school.location_long.toDouble()
                var distanceResult = currentLocation.distanceTo(schoolLocation)
                distanceResult = Math.round(distanceResult / 1000).toFloat()
                school.distanceTo = distanceResult
            }
            operationOnList(listItems)
        }
    }

    fun operationOnList(listItems: List<School>){
        var sortHandler = SortListItems()
        var listSorted = sortHandler.sortListAsc(listItems)
        presenter.sortListItem(listSorted)
    }
}
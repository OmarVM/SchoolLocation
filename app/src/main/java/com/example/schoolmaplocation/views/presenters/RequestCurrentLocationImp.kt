package com.example.schoolmaplocation.views.presenters

import android.content.Context
import com.example.schoolmaplocation.logic.interactors.GetCurrentLocation
import com.example.schoolmaplocation.views.CurrentLocationUI

class RequestCurrentLocationImp : CurrentLocationPres {

    private var view: CurrentLocationUI
    private var interactor: GetCurrentLocation

    constructor(view: CurrentLocationUI, context:Context) {
        this.view = view
        interactor = GetCurrentLocation(context, this)
        interactor.getCurrentLocation()
    }


    override fun userCurrentLocation(lat: Double, long: Double) {
        view.userCurrentLocation(lat, long)
    }

}
package com.example.schoolmaplocation.logic.interactors

import android.content.Context
import com.example.schoolmaplocation.logic.data.InfoRequestOperation
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.logic.data.network.volley.VolleyMainRequest
import com.example.schoolmaplocation.tools.Server
import com.example.schoolmaplocation.views.presenters.ListSchools

class DataManager : InfoRequestOperation {

    private var presenter: ListSchools
    private var hasPermission = false

    constructor(presenter: ListSchools, context: Context, location_lat: Double, location_long: Double) {
        this.presenter = presenter
        var requestAPIExt = VolleyMainRequest()
        var finalURL = getURL(location_lat, location_long)
        requestAPIExt.getListSchool(context, finalURL, this)
    }


    override fun OperationSuccess(listItems: List<School>) {
        presenter.OperationSuccess(listItems, hasPermission)
    }

    override fun OperationError(messageError: String, typeError: Int) {
        presenter.OperationError(messageError, typeError)
    }

    private fun getURL(location_lat: Double, location_long: Double): String{

        if (location_lat != 0.0 && location_long != 0.0){
            hasPermission = true
            return Server.URL_1 + location_lat + "," + location_long + Server.URL_2
        }else{
            hasPermission = false
            return Server.TEST_URL
        }
    }
}
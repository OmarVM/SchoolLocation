package com.example.schoolmaplocation.views.presenters

import android.content.Context
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.logic.interactors.DataManager
import com.example.schoolmaplocation.views.ListSchoolsUI

class RequestListSchoolsImp : ListSchools {

    private var view: ListSchoolsUI
    private var dataManager: DataManager

    constructor(view: ListSchoolsUI, context: Context, location_lat: Double, location_long: Double) {
        this.view = view
        this.dataManager = DataManager(this, context, location_lat, location_long)
    }


    override fun doRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OperationSuccess(listItems: List<School>, hasPermission: Boolean) {
        view.listInfo(listItems,hasPermission)
    }

    override fun OperationError(messageError: String, typeError: Int) {
        view.listInfoError(messageError, typeError)
    }
}
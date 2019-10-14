package com.example.schoolmaplocation.views.presenters

import com.example.schoolmaplocation.logic.data.models.School

interface ListSchools {

    fun doRequest()
    fun OperationSuccess(listItems: List<School>, hasPermission: Boolean)
    fun OperationError(messageError: String, typeError: Int)
}
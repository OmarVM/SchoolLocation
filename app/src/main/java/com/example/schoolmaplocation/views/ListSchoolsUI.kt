package com.example.schoolmaplocation.views

import com.example.schoolmaplocation.logic.data.models.School

interface ListSchoolsUI {

    fun listInfo(listItems: List<School>, hasPermission: Boolean)
    fun listInfoError(messageError: String, typeError: Int)
}
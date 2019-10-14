package com.example.schoolmaplocation.views.presenters

import com.example.schoolmaplocation.logic.data.models.School

interface FilterByDistancePre {
    fun sortListItem(listItems: List<School>)
}
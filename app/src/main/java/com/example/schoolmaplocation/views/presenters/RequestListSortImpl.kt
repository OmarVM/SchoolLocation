package com.example.schoolmaplocation.views.presenters

import android.content.Context
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.logic.interactors.CalculateDistance
import com.example.schoolmaplocation.views.FilterDistanceUI

class RequestListSortImpl : FilterByDistancePre {

    private var context: Context
    private var listItems: List<School>
    private var view: FilterDistanceUI

    constructor(context: Context, listItems: List<School>, view: FilterDistanceUI) {
        this.context = context
        this.listItems = listItems
        this.view = view

        val filter = CalculateDistance(context, this)
        filter.setDistace(listItems)
    }


    override fun sortListItem(listItems: List<School>) {
        view.sortListItem(listItems)
    }
}
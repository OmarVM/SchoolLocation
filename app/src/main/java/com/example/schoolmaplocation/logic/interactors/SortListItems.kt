package com.example.schoolmaplocation.logic.interactors

import android.util.Log
import com.example.schoolmaplocation.logic.data.models.School

class SortListItems {


    fun sortListAsc(listItemsAux: List<School>): List<School>{
        var listItems = listItemsAux as ArrayList
        listItems.sortBy { filterByDistance(it) }
        //listItems.forEach { Log.d("TAG","Lista Ordenada -> " +  it.distanceTo.toString()) }

        return listItems
    }

    fun filterByDistance(school: School): Float = school.distanceTo
}
package com.example.schoolmaplocation.logic.data

import com.example.schoolmaplocation.logic.data.models.School

interface InfoRequestOperation {

    fun OperationSuccess(listItems: List<School>)
    fun OperationError(messageError: String, typeError: Int)
}
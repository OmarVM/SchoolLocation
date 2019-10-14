package com.example.schoolmaplocation.logic.data.models

import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

data class School(
    val formatted_address : String,
    //val geometry : JSONObject,
    val icon : String,
    val id: String,
    val name: String,
    val place_id: String,
    //val plus_code: JSONObject,
    val rating: Int,
    val reference: String,
    //val types: JSONArray,
    val user_ratings_total: Int,
    val location_lat: Double,
    val location_long: Double,
    var distanceTo: Float) : Serializable {
}
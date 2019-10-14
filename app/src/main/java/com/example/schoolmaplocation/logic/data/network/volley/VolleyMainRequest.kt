package com.example.schoolmaplocation.logic.data.network.volley

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.schoolmaplocation.logic.data.InfoRequestOperation
import com.example.schoolmaplocation.logic.data.models.School
import com.example.schoolmaplocation.tools.Constants
import org.json.JSONObject

class VolleyMainRequest {

    fun getListSchool(context: Context, URL : String, listener: InfoRequestOperation){

        val listSchool =  arrayListOf<School>()
        val queue = Volley.newRequestQueue(context)

        val stringRequest = JsonObjectRequest(Request.Method.GET, URL, null,
            Response.Listener<JSONObject> { response ->

                val schools = response.getJSONArray(Constants.JSON_RESULTS)
                for (i in 0  until schools.length()){
                    val mjsonObject = schools.getJSONObject(i)
                    val anSchool = School(mjsonObject.getString(Constants.JSON_FORMATTED_ADDRESS),
                                        //mjsonObject.getJSONObject(Constants.JSON_GEOMETRY),
                                        mjsonObject.getString(Constants.JSON_ICON),
                                        mjsonObject.getString(Constants.JSON_ID),
                                        mjsonObject.getString(Constants.JSON_NAME),
                                        mjsonObject.getString(Constants.JSON_PLACE_ID),
                                        //mjsonObject.getJSONObject(Constants.JSON_PLUS_CODE),
                                        mjsonObject.getInt(Constants.JSON_RATING),
                                        mjsonObject.getString(Constants.JSON_REFERENCE),
                                        //mjsonObject.getJSONArray(Constants.JSON_TYPES),
                                        mjsonObject.getInt(Constants.JSON_USER_RATINGS_TOTAL),
                                        mjsonObject.getJSONObject(Constants.JSON_GEOMETRY).getJSONObject(Constants.JSON_LOCATION).getDouble(Constants.JSON_LOCATION_LAT),
                                        mjsonObject.getJSONObject(Constants.JSON_GEOMETRY).getJSONObject(Constants.JSON_LOCATION).getDouble(Constants.JSON_LOCATION_LONG),
                                        0.0F)

                    listSchool.add(anSchool)
                }
                listener.OperationSuccess(listSchool)

        }, Response.ErrorListener {
                Log.d("TAG", "Respuesta Error -> ${it.message}")
                listener.OperationError(it.message.toString(), it.hashCode())
            })

        queue.add(stringRequest)
    }
}
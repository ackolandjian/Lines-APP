package com.ismin.projectapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LineService {
    @GET("lignes")
    fun getAllLines(): Call<ArrayList<Line>>

    @POST("lignes")
    fun createLine(@Body() line: Line): Call<Line>

    @GET(":name_line")
    fun getLine():Call<Line>

}

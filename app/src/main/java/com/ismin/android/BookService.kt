package com.ismin.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookService {

    @GET("lignes")
    fun getAllBooks(): Call<ArrayList<Book>>

    @POST("lignes")
    fun createBook(@Body() book: Book): Call<Book>

    @GET(":title")
    fun getBook() : Call<Book>
}
package com.example.catapp.network

import com.example.catapp.model.ObjectDetails
import com.example.catapp.model.Picture
import com.example.catapp.model.PictureResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MetMuseumApi {
    @GET("objects")
    suspend fun getObjects(): PictureResponse

    @GET("objects/{objectID}")
    suspend fun getObjectDetails(@Path("objectID") objectID: Int): ObjectDetails

}
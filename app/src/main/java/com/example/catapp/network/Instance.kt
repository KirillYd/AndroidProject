package com.example.catapp.network


import com.example.catapp.model.PictureResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Instance(client: OkHttpClient) {
    private val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

    val api: MetMuseumApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetMuseumApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"
    }
}
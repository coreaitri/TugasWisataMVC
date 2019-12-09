package com.android.tugasmvc.Galery

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http:172.16.10.116/myfdp/api.php/"

interface GaleryAPI {

    @GET("galery")
    fun getGalery() : Call<List<Galery>>

    companion object {
        operator fun invoke() : GaleryAPI{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GaleryAPI::class.java)
        }
    }
}
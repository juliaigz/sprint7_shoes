package com.example.shoes.modelo.remote.apiRetrofit

import com.example.shoes.modelo.remote.fromInternet.DetailsShoesApiClass
import com.example.shoes.modelo.remote.fromInternet.ShoesApiClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoesApi {

    // es el que te agrega los links  products y el otro que dice details

    //listado de zapatos
    @GET("shoes")
    suspend fun fetchShoesList(): Response<List<ShoesApiClass>>

    //seleccionar un zapato con detalle
    @GET("shoes/{id}")
    suspend fun fechShoesDetail(@Path("id") id: String): Response<DetailsShoesApiClass>
}
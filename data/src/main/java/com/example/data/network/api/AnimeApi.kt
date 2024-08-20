package com.example.data.network.api

import com.example.data.network.models.AnimeObjectNetwork
import com.example.data.network.models.AnimeListNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): AnimeListNetwork

    @GET("anime/{id}")
    suspend fun getAnime(@Path("id") id: Int): AnimeObjectNetwork
}
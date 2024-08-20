package com.example.data.network.api

import com.example.data.network.models.AnimeObjectModel
import com.example.data.network.models.AnimeListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): AnimeListModel

    @GET("anime/{id}")
    suspend fun getAnime(@Path("id") id: Int): AnimeObjectModel
}
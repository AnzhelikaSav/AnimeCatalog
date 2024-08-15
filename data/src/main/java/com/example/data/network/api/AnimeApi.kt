package com.example.data.network.api

import com.example.data.network.models.AnimeObjectModel
import com.example.data.network.models.AnimeListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListModel

    @GET("anime/{id}")
    suspend fun getAnime(@Path("id") id: Int): AnimeObjectModel
}
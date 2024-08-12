package com.example.data.network.api

import com.example.data.network.models.AnimeTopListModel
import retrofit2.http.GET

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeTopListModel
}
package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeNetwork(
    @SerializedName("mal_id")
    val id: Int,
    @SerializedName("images")
    val images: ImageModel,
    @SerializedName("titles")
    val titles: List<TitleNetwork>,
    @SerializedName("score")
    val score: Double,
    @SerializedName("year")
    val year: Int,
    @SerializedName("genres")
    val genres: List<GenreNetwork>,
    @SerializedName("synopsis")
    val synopsis: String
)
package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeListNetwork(
    @SerializedName("data")
    val data: List<AnimeNetwork>,
    @SerializedName("pagination")
    val pagination: Pagination
)
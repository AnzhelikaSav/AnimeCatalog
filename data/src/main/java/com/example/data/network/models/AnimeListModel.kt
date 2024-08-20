package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeListModel(
    @SerializedName("data")
    val data: List<AnimeModel>,
    @SerializedName("pagination")
    val pagination: Pagination
)
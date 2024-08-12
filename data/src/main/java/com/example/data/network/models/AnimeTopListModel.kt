package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeTopListModel(
    @SerializedName("data")
    val data: List<AnimeModel>
)
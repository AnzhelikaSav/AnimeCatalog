package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeObjectModel(
    @SerializedName("data")
    val data: AnimeModel
)
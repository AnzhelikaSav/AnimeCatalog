package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class AnimeObjectNetwork(
    @SerializedName("data")
    val data: AnimeNetwork
)
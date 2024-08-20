package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class TitleModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("title")
    val title: String
)
package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("jpg")
    val jpg: JPGModel
)

data class JPGModel(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String
)
package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean
)
package com.example.data.mappers

import com.example.data.network.models.AnimeModel
import com.example.domain.models.Anime

fun AnimeModel.toDomain(): Anime {
    return Anime(
        id = this.id,
        imageUrl = this.images.jpg.imageUrl,
        largeImageUrl = this.images.jpg.largeImageUrl,
        title = this.title,
        score = this.score,
        year = this.year,
        genres = this.genres.map { it.name },
        synopsis = this.synopsis
    )
}
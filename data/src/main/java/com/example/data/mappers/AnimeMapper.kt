package com.example.data.mappers

import com.example.data.database.entities.FavoriteEntity
import com.example.data.network.models.AnimeNetwork
import com.example.domain.models.Anime

private const val SEPARATOR = " "

fun AnimeNetwork.toDomain(): Anime {
    return Anime(
        id = this.id,
        imageUrl = this.images?.jpg?.imageUrl ?: "",
        largeImageUrl = this.images?.jpg?.largeImageUrl ?: "",
        title = this.titles?.firstOrNull { it.type == "Default" }?.title ?:
            this.titles?.first { it.title.isNotEmpty() }?.title ?: "",
        score = this.score ?: 0.0,
        year = this.year ?: 0,
        genres = this.genres?.map { it.name } ?: emptyList(),
        synopsis = this.synopsis ?: ""
    )
}

fun FavoriteEntity.toDomain() : Anime{
    return Anime(
        id = this.id,
        imageUrl = this.imageUrl,
        largeImageUrl = this.largeImageUrl,
        title = this.title,
        score = this.score,
        year = this.year,
        genres = this.genres.split(SEPARATOR),
        synopsis = this.synopsis
    )
}

fun Anime.toDatabase(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id,
        imageUrl = this.imageUrl,
        largeImageUrl = this.largeImageUrl,
        title = this.title,
        score = this.score,
        year = this.year,
        genres = this.genres.joinToString(SEPARATOR),
        synopsis = this.synopsis
    )
}
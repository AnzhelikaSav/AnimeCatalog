package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteEntity.TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = LARGE_IMAGE_URL) val largeImageUrl: String,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = SCORE) val score: Double,
    @ColumnInfo(name = YEAR) val year: Int,
    @ColumnInfo(name = GENRES) val genres: String,
    @ColumnInfo(name = SYNOPSIS) val synopsis: String
) {
    companion object {
        const val TABLE_NAME = "anime"
        const val ID = "id"
        const val IMAGE_URL = "image_url"
        const val LARGE_IMAGE_URL = "large_image_url"
        const val TITLE = "title"
        const val SCORE = "score"
        const val YEAR = "year"
        const val GENRES = "genres"
        const val SYNOPSIS = "synopsis"
    }
}
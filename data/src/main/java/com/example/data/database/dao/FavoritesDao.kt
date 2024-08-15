package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * from ${FavoriteEntity.TABLE_NAME}")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Query("SELECT * from ${FavoriteEntity.TABLE_NAME} " +
            "WHERE ${FavoriteEntity.ID} = :id")
    fun get(id: Int): Flow<FavoriteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(favoriteEntity: FavoriteEntity)

    @Query("DELETE from ${FavoriteEntity.TABLE_NAME} " +
            "WHERE ${FavoriteEntity.ID} = :id")
    suspend fun delete(id: Int)
}
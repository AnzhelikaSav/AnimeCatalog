package com.example.animecatalog.di

import android.content.Context
import com.example.data.database.AppDatabase
import com.example.data.database.dao.FavoritesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.create(context)
    }

    @Singleton
    @Provides
    fun provideFavoritesDao(appDatabase: AppDatabase): FavoritesDao {
        return appDatabase.favoritesDao()
    }
}
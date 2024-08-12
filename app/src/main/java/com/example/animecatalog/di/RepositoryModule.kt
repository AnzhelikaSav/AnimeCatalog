package com.example.animecatalog.di

import com.example.data.repository.AnimeRepositoryImpl
import com.example.domain.repository.AnimeRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository
}
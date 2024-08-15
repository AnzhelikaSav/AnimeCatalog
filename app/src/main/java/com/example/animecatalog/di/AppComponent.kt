package com.example.animecatalog.di

import com.example.animecatalog.MainActivity
import com.example.animecatalog.features.details.AnimeDetailsFragment
import com.example.animecatalog.features.top_list.AnimeTopListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: AnimeTopListFragment)
    fun inject(activity: AnimeDetailsFragment)
}
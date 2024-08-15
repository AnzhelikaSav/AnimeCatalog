package com.example.animecatalog.di

import android.content.Context
import com.example.animecatalog.MainActivity
import com.example.animecatalog.features.details.AnimeDetailsFragment
import com.example.animecatalog.features.favorites.FavoritesAnimeFragment
import com.example.animecatalog.features.top_list.AnimeTopListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, AppModule::class, DataModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: AnimeTopListFragment)
    fun inject(fragment: AnimeDetailsFragment)
    fun inject(fragment: FavoritesAnimeFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }
}
package com.example.animecatalog.features.top_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.example.domain.usecase.GetTopAnimeUseCase
import javax.inject.Inject

class AnimeTopListViewModel(
    getTopAnimeUseCase: GetTopAnimeUseCase
) : ViewModel() {

    val animeFlow = getTopAnimeUseCase
        .execute()
        .cachedIn(viewModelScope)

}

class AnimeTopListViewModelFactory @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AnimeTopListViewModel(getTopAnimeUseCase) as T
    }
}
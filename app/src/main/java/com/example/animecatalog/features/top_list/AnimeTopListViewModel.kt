package com.example.animecatalog.features.top_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.Anime
import com.example.domain.usecase.GetTopAnimeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeTopListViewModel(
    private val getTopAnimeUseCase: GetTopAnimeUseCase
) : ViewModel() {

    private val _animeList: MutableLiveData<List<Anime>> = MutableLiveData()
    val animeList: LiveData<List<Anime>> = _animeList

    fun getTopAnime() {
        viewModelScope.launch {
            _animeList.value = getTopAnimeUseCase.execute()
        }
    }
}

class AnimeTopListViewModelFactory @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AnimeTopListViewModel(getTopAnimeUseCase) as T
    }
}
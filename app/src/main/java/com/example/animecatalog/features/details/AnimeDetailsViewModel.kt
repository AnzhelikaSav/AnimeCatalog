package com.example.animecatalog.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.Anime
import com.example.domain.usecase.AddToFavoritesUseCase
import com.example.domain.usecase.CheckIsFavoriteUseCase
import com.example.domain.usecase.DeleteFromFavoritesUseCase
import com.example.domain.usecase.GetAnimeDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val id: Int,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase
): ViewModel() {

    private val _anime: MutableLiveData<Anime> = MutableLiveData()
    val anime: LiveData<Anime> = _anime

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        viewModelScope.launch {
            _anime.value = getAnimeDetailsUseCase.execute(id)
        }
        viewModelScope.launch {
            checkIsFavoriteUseCase.execute(id).collect {
                 _isFavorite.value = it
            }
        }
    }

    fun onAddToFavoritesClick() {
        if (_isFavorite.value == false) {
            addToFavorites()
        } else if (_isFavorite.value == true)  {
            deleteFromFavorites()
        }
    }

    private fun addToFavorites() {
        viewModelScope.launch {
            _anime.value?.let {
                addToFavoritesUseCase.execute(it)
            }
        }
    }

    private fun deleteFromFavorites() {
        viewModelScope.launch {
            _anime.value?.let {
                deleteFromFavoritesUseCase.execute(id)
            }
        }
    }
}

@AssistedFactory
interface AnimeDetailsViewModelFactoryAssisted {
    fun create(id: Int): AnimeDetailsViewModelFactory
}

class AnimeDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val id: Int,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val checkIsFavoriteUseCase: CheckIsFavoriteUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AnimeDetailsViewModel(
            id = id,
            getAnimeDetailsUseCase = getAnimeDetailsUseCase,
            addToFavoritesUseCase = addToFavoritesUseCase,
            deleteFromFavoritesUseCase = deleteFromFavoritesUseCase,
            checkIsFavoriteUseCase = checkIsFavoriteUseCase
        ) as T
    }
}
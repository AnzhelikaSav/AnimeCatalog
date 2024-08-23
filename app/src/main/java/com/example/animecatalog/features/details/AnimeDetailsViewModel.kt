package com.example.animecatalog.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.RequestResult
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
) : ViewModel() {

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _result: MutableLiveData<RequestResult<Anime>> = MutableLiveData()
    val result: LiveData<RequestResult<Anime>> = _result

    init {
        loadInfo()
        viewModelScope.launch {
            checkIsFavoriteUseCase.execute(id).collect {
                _isFavorite.value = it
            }
        }
    }

    fun onAddToFavoritesClick() {
        if (_isFavorite.value == false) {
            addToFavorites()
        } else if (_isFavorite.value == true) {
            deleteFromFavorites()
        }
    }

    fun onRetryClick() {
        loadInfo()
    }

    private fun loadInfo() {
        viewModelScope.launch {
            _result.value = RequestResult.Loading
            _result.value = getAnimeDetailsUseCase.execute(id)
        }
    }

    private fun addToFavorites() {
        viewModelScope.launch {
            if (_result.value is RequestResult.Success) {
                try {
                    val anime = (_result.value as RequestResult.Success).data
                    addToFavoritesUseCase.execute(anime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun deleteFromFavorites() {
        viewModelScope.launch {
            if (_result.value is RequestResult.Success) {
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
) : ViewModelProvider.Factory {
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
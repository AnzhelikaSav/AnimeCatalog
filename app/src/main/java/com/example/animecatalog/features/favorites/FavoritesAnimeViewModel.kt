package com.example.animecatalog.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.Anime
import com.example.domain.usecase.DeleteFromFavoritesUseCase
import com.example.domain.usecase.GetFavoritesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesAnimeViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
): ViewModel() {

    private val _favorites: MutableLiveData<List<Anime>> = MutableLiveData()
    val favorites: LiveData<List<Anime>> = _favorites

    init {
        viewModelScope.launch {
            getFavoritesUseCase.execute().collect {
                _favorites.value = it
            }
        }
    }

    fun onDeleteClick(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase.execute(id)
        }
    }
}

class FavoritesAnimeViewModelFactory @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return FavoritesAnimeViewModel(
            getFavoritesUseCase = getFavoritesUseCase,
            deleteFromFavoritesUseCase = deleteFromFavoritesUseCase
        ) as T
    }
}
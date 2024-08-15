package com.example.animecatalog.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.Anime
import com.example.domain.usecase.GetAnimeDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val id: Int,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
): ViewModel() {

    private val _anime: MutableLiveData<Anime> = MutableLiveData()
    val anime: LiveData<Anime> = _anime

    init {
        viewModelScope.launch {
            _anime.value = getAnimeDetailsUseCase.execute(id)
        }
    }
}

@AssistedFactory
interface AnimeDetailsViewModelFactoryAssisted {
    fun create(id: Int): AnimeDetailsViewModelFactory
}

class AnimeDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val id: Int,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AnimeDetailsViewModel(
            id = id,
            getAnimeDetailsUseCase = getAnimeDetailsUseCase
        ) as T
    }
}
package com.example.animecatalog.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.Anime
import com.example.domain.usecase.SearchAnimeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val TIMEOUT = 500L

class SearchAnimeViewModel(
    private val searchAnimeUseCase: SearchAnimeUseCase
): ViewModel() {
    private val _query: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val animeList: Flow<PagingData<Anime>> = _query
        .debounce(TIMEOUT)
        .distinctUntilChanged()
        .flatMapLatest {
            searchAnimeUseCase.execute(_query.value)
        }
        .cachedIn(viewModelScope)

    fun onQueryChange(value: String) {
        _query.value = value
    }
}

class SearchAnimeViewModelFactory @Inject constructor(
    private val searchAnimeUseCase: SearchAnimeUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SearchAnimeViewModel(searchAnimeUseCase) as T
    }
}
package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.domain.usecase.GetFavoriteMoviesListUseCase
import com.suslanium.filmus.presentation.state.FavoritesListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val getFavoriteMoviesListUseCase: GetFavoriteMoviesListUseCase
) : ViewModel() {

    val favoritesListState: State<FavoritesListState>
        get() = _favoritesListState
    private val _favoritesListState = mutableStateOf<FavoritesListState>(FavoritesListState.Loading)

    init {
        loadData()
    }

    fun loadData() {
        _favoritesListState.value = FavoritesListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = getFavoriteMoviesListUseCase()
                withContext(Dispatchers.Main) {
                    _favoritesListState.value = FavoritesListState.Content(movies)
                }
            } catch (_: Exception) {
                withContext(Dispatchers.Main) {
                    _favoritesListState.value = FavoritesListState.Error
                }
            }
        }
    }

}
package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.domain.usecase.GetFavoriteMoviesListUseCase
import com.suslanium.filmus.domain.usecase.LogoutUseCase
import com.suslanium.filmus.presentation.common.ErrorCodes
import com.suslanium.filmus.presentation.state.FavoritesListState
import com.suslanium.filmus.presentation.state.LogoutEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class FavoriteViewModel(
    private val getFavoriteMoviesListUseCase: GetFavoriteMoviesListUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val favoritesListState: State<FavoritesListState>
        get() = _favoritesListState
    private val _favoritesListState = mutableStateOf<FavoritesListState>(FavoritesListState.Loading)

    private val _logoutEventChannel = Channel<LogoutEvent>()
    val logoutEvents = _logoutEventChannel.receiveAsFlow()

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
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else withContext(Dispatchers.Main) {
                    _favoritesListState.value = FavoritesListState.Error
                }
            }
        }
    }

    fun removeMovie(id: UUID) {
        viewModelScope.launch {
            val favoritesList = _favoritesListState.value
            if (favoritesList is FavoritesListState.Content) {
                val movies = favoritesList.movies.toMutableList()
                movies.removeIf { it.id == id }
                _favoritesListState.value = FavoritesListState.Content(movies)
            }
        }
    }

    fun modifyMovie(id: UUID, newUserRating: Int?) {
        viewModelScope.launch {
            val favoritesList = _favoritesListState.value
            if (favoritesList is FavoritesListState.Content) {
                val movies = favoritesList.movies.toMutableList()
                movies.forEachIndexed { index, movieSummary ->
                    if (movieSummary.id == id) {
                        movies[index] = movieSummary.copy(userRating = newUserRating)
                    }
                }
                _favoritesListState.value = FavoritesListState.Content(movies)
            }
        }
    }

    private suspend fun logout() {
        try {
            logoutUseCase()
        } finally {
            withContext(Dispatchers.Main) {
                _logoutEventChannel.send(LogoutEvent.Logout)
            }
        }
    }

}
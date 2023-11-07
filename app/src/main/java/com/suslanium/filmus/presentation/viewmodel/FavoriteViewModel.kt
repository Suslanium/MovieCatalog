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
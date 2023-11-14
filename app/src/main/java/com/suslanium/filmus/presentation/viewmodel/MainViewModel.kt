package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.usecase.GetMoviesListUseCase
import com.suslanium.filmus.presentation.state.ModifiedMovie
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class MainViewModel(
    getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel() {

    val movies: Flow<PagingData<MovieSummary>> = getMoviesListUseCase().cachedIn(viewModelScope)

    val modifiedMovies: SnapshotStateMap<UUID, ModifiedMovie>
        get() = _modifiedMovies
    private val _modifiedMovies = mutableStateMapOf<UUID, ModifiedMovie>()


    fun setModifiedMovie(id: UUID, rating: Float?, userRating: Int?) {
        _modifiedMovies[id] = ModifiedMovie(newRating = rating, newUserRating = userRating)
    }

    fun clearModifiedMovies() {
        _modifiedMovies.clear()
    }
}
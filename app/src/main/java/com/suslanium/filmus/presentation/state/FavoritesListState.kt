package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.domain.entity.movie.MovieSummary

sealed interface FavoritesListState {

    object Loading: FavoritesListState

    object Error: FavoritesListState

    data class Content(val movies: List<MovieSummary>): FavoritesListState

}
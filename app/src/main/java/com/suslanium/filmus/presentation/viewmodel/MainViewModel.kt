package com.suslanium.filmus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel(){

   val movies: Flow<PagingData<MovieSummary>> = getMoviesListUseCase().cachedIn(viewModelScope)

}
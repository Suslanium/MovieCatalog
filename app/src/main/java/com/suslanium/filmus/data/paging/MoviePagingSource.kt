package com.suslanium.filmus.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suslanium.filmus.data.converter.MovieSummaryConverter
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.domain.entity.movie.MovieSummary

class MoviePagingSource(
    private val movieApiService: MovieApiService
) : PagingSource<Int, MovieSummary>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSummary>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSummary> {
        return try {
            val currentPage = params.key ?: 1
            val moviesPage = movieApiService.getMoviesList(currentPage)
            LoadResult.Page(
                data = moviesPage.movies?.map { movieElementModel ->
                    MovieSummaryConverter.convert(
                        movieElementModel
                    )
                }
                    ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (moviesPage.movies.isNullOrEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
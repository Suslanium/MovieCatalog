package com.suslanium.filmus.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suslanium.filmus.data.converter.MovieSummaryConverter
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.model.MovieElementModel
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import java.util.UUID

class MoviePagingSource(
    private val movieApiService: MovieApiService,
    private val userDataSource: UserDataSource,
    private val fetchUserRating: suspend (MovieApiService, MovieElementModel, UUID) -> Int?
) : PagingSource<Int, MovieSummary>() {

    private lateinit var userId: UUID

    override fun getRefreshKey(state: PagingState<Int, MovieSummary>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSummary> {
        return try {
            val currentPage = params.key ?: 1
            val moviesPage = movieApiService.getMoviesList(currentPage)
            if (!this::userId.isInitialized) userId = userDataSource.fetchUserId()
            val movies = moviesPage.movies?.map { movieElementModel ->
                MovieSummaryConverter.convert(
                    movieElementModel, fetchUserRating(movieApiService, movieElementModel, userId)
                )
            } ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (moviesPage.movies.isNullOrEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
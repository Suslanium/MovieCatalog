package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.domain.entity.review.ReviewRequest
import com.suslanium.filmus.domain.usecase.AddFavoriteUseCase
import com.suslanium.filmus.domain.usecase.AddReviewUseCase
import com.suslanium.filmus.domain.usecase.DeleteReviewUseCase
import com.suslanium.filmus.domain.usecase.EditReviewUseCase
import com.suslanium.filmus.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.filmus.domain.usecase.LogoutUseCase
import com.suslanium.filmus.domain.usecase.RemoveFavoriteUseCase
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.common.ErrorCodes
import com.suslanium.filmus.presentation.state.DetailsState
import com.suslanium.filmus.presentation.state.LogoutEvent
import com.suslanium.filmus.presentation.state.ReviewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.time.format.DateTimeFormatter
import java.util.UUID

class DetailsViewModel(
    private val movieId: UUID,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val addReviewUseCase: AddReviewUseCase,
    private val editReviewUseCase: EditReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val detailsState: State<DetailsState>
        get() = _detailsState
    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Loading)

    val detailsData: State<MovieDetails>
        get() = _detailsData
    private val _detailsData = mutableStateOf(
        MovieDetails(
            id = UUID.randomUUID(),
            isFavorite = false,
            name = null,
            posterUri = null,
            year = 0,
            country = null,
            genres = emptyList(),
            userReview = null,
            reviews = emptyList(),
            lengthMinutes = 0,
            tagLine = null,
            description = null,
            director = null,
            budget = null,
            fees = null,
            minimalAge = 0,
            rating = null
        )
    )

    val reviewData: State<ReviewRequest>
        get() = _reviewData

    private val _reviewData = mutableStateOf(
        ReviewRequest(
            reviewText = Constants.EMPTY_STRING,
            rating = 0,
            isAnonymous = false
        )
    )

    val reviewState: State<ReviewState>
        get() = _reviewState
    private val _reviewState = mutableStateOf<ReviewState>(ReviewState.DialogClosed)

    val canSaveReview = derivedStateOf {
        (_reviewData.value.reviewText != _detailsData.value.userReview?.reviewText.orEmpty() ||
                _reviewData.value.rating != (_detailsData.value.userReview?.rating ?: 0) ||
                _reviewData.value.isAnonymous != (_detailsData.value.userReview?.isAnonymous
            ?: false)) &&
                _reviewData.value.reviewText.isNotBlank()
    }

    val reviewIsAnonymousAvailable = derivedStateOf {
        _detailsData.value.userReview == null || (_detailsData.value.userReview?.isAnonymous ?: false)
    }

    private val _logoutEventChannel = Channel<LogoutEvent>()
    val logoutEvents = _logoutEventChannel.receiveAsFlow()

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    init {
        loadFilmData()
    }

    fun loadFilmData() {
        _detailsState.value = DetailsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movie = getMovieDetailsUseCase(movieId)
                val userReview = movie.userReview
                withContext(Dispatchers.Main) {
                    _detailsData.value = movie
                    if (userReview != null) {
                        _reviewData.value = ReviewRequest(
                            userReview.reviewText.orEmpty(),
                            userReview.rating,
                            userReview.isAnonymous
                        )
                    }
                    _detailsState.value = DetailsState.Content
                }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else _detailsState.value = DetailsState.Error
            }
        }
    }

    fun setReviewText(text: String) {
        if (_reviewState.value == ReviewState.Error) _reviewState.value = ReviewState.Editing
        _reviewData.value = _reviewData.value.copy(reviewText = text)
    }

    fun setReviewRating(rating: Int) {
        if (_reviewState.value == ReviewState.Error) _reviewState.value = ReviewState.Editing
        _reviewData.value = _reviewData.value.copy(rating = rating)
    }

    fun setReviewIsAnonymous(isAnonymous: Boolean) {
        if (_reviewState.value == ReviewState.Error) _reviewState.value = ReviewState.Editing
        _reviewData.value = _reviewData.value.copy(isAnonymous = isAnonymous)
    }

    fun openReviewDialog() {
        _reviewState.value = ReviewState.Editing
    }

    fun closeReviewDialog() {
        if (_reviewState.value == ReviewState.Saving) return
        _reviewState.value = ReviewState.DialogClosed
        _reviewData.value = ReviewRequest(
            _detailsData.value.userReview?.reviewText.orEmpty(),
            _detailsData.value.userReview?.rating ?: 0,
            _detailsData.value.userReview?.isAnonymous ?: false
        )
    }

    fun deleteReview() {
        _reviewState.value = ReviewState.Deleting
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userReviewId = _detailsData.value.userReview?.id
                deleteReviewUseCase(
                    _detailsData.value.id,
                    userReviewId ?: _detailsData.value.reviews[0].id
                )
                val movie = getMovieDetailsUseCase(_detailsData.value.id)
                _detailsData.value = movie
                _reviewState.value = ReviewState.DialogClosed
                _reviewData.value = ReviewRequest(
                    reviewText = Constants.EMPTY_STRING,
                    rating = 0,
                    isAnonymous = false
                )
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else _reviewState.value = ReviewState.DialogClosed
            }
        }
    }

    fun saveReview() {
        _reviewState.value = ReviewState.Saving
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userReview = _detailsData.value.userReview
                if (userReview != null) {
                    editReviewUseCase(_detailsData.value.id, userReview.id, _reviewData.value)
                } else {
                    addReviewUseCase(_detailsData.value.id, _reviewData.value)
                }
                val movie = getMovieDetailsUseCase(_detailsData.value.id)
                _detailsData.value = movie
                _reviewState.value = ReviewState.DialogClosed
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else _reviewState.value = ReviewState.Error
            }
        }
    }

    fun changeFavoritesState() {
        val prevFavoriteState = _detailsData.value.isFavorite
        _detailsData.value = _detailsData.value.copy(isFavorite = !prevFavoriteState)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (prevFavoriteState) {
                    removeFavoriteUseCase(_detailsData.value.id)
                } else {
                    addFavoriteUseCase(_detailsData.value.id)
                }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else _detailsData.value = _detailsData.value.copy(isFavorite = prevFavoriteState)
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
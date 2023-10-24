package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.GenreModel
import com.suslanium.filmus.domain.entity.movie.Genre
import java.util.UUID

object GenreConverter {
    fun convertGenreList(genres: List<GenreModel>?): List<Genre> {
        if (genres.isNullOrEmpty()) return emptyList()
        return genres.map { genreModel -> Genre(UUID.fromString(genreModel.id), genreModel.name) }
    }
}
package com.nandomiranda.themovies.view.interfaces

import com.nandomiranda.themovies.model.movie.Movie

interface MovieSelectListener {
    fun onMovieSelected(movie: Movie)
}
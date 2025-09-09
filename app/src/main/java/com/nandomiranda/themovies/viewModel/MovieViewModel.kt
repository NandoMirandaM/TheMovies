package com.nandomiranda.themovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.model.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private var _movieList = MutableLiveData<MutableList<Movie>>()

    val movieList: LiveData<MutableList<Movie>>
        get() = _movieList

    private val repository = MovieRepository()

    init {
        viewModelScope.launch {
            _movieList.value = repository.fetchMoviePopular()
        }
    }

}
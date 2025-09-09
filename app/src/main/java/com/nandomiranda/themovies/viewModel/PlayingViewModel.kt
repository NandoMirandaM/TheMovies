package com.nandomiranda.themovies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.model.repository.MovieRepository
import kotlinx.coroutines.launch

class PlayingViewModel: ViewModel() {
    private var _playingList = MutableLiveData<MutableList<Movie>>()

    val playingList: LiveData<MutableList<Movie>>
        get() = _playingList

    private val repository = MovieRepository()

    init {
        viewModelScope.launch {
           _playingList.value = repository.fetchMoviePlaying()
        }
    }
}
package com.nandomiranda.themovies.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.model.repository.MovieRepository
import com.nandomiranda.themovies.model.video.VideoMovie
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(application: Application , id:Int): AndroidViewModel(application) {
    private var _videoList = MutableLiveData<MutableList<VideoMovie>>()

    val videoList: LiveData<MutableList<VideoMovie>>
        get() = _videoList

    val repository = MovieRepository()

    init {
        viewModelScope.launch {
            try {
                VideoJson(id)
            }catch (e: Exception){
                Toast.makeText(application,"No se pudo cargar el video",Toast.LENGTH_SHORT).show()
            }

        }
    }

    suspend fun VideoJson(id: Int){
        _videoList.value = repository.fetchVideo(id)
    }

}
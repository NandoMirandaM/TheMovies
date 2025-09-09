package com.nandomiranda.themovies.model.repository

import android.util.Log
import com.nandomiranda.themovies.model.api.movieJson.MovieJsonResponse
import com.nandomiranda.themovies.model.api.service
import com.nandomiranda.themovies.model.api.videoJson.VideoJsonResponse
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.model.video.VideoMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val YOUTUBE="https://youtu.be/"
class MovieRepository {
    suspend fun fetchMoviePopular(): MutableList<Movie> {
        return withContext(Dispatchers.IO){
            val movieListJson = service.getPopularMovie()
            val movieList = parseMovieResult(movieListJson)

            movieList
        }
    }

    private fun parseMovieResult(movieJsonResponse: MovieJsonResponse):MutableList<Movie> {
        val movieList = mutableListOf<Movie>()
        val resultList = movieJsonResponse.results

        for (result in resultList){
            val id = result.id
            val overview = result.overview
            val image= result.poster_path
            val title = result.title

            movieList.add(Movie(id,overview,image,title))
        }

        return movieList
    }

    suspend fun fetchMoviePlaying(): MutableList<Movie> {
        return withContext(Dispatchers.IO){
            val playingListJson = service.getPlayingMovie()
            val playList = parseMovieResult(playingListJson)

            playList
        }
    }

    suspend fun fetchVideo(id:Int): MutableList<VideoMovie> {
        return withContext(Dispatchers.IO){
            val videoListJson = service.getVideoMovie(id)
            val video = parseVideoResult(videoListJson)

            video
        }
    }

    private fun parseVideoResult(videoJsonResponse: VideoJsonResponse):MutableList<VideoMovie> {
        val video = mutableListOf<VideoMovie>()
        val id= videoJsonResponse.id
        val result = videoJsonResponse.results
            val key = result[0].key
            val site= result[0].site
            var urlVideo = ""
            if(site=="YouTube"){
                urlVideo= "$YOUTUBE$key"
            }
            video.add(VideoMovie(id,urlVideo))

        return video
    }

}
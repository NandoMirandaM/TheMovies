package com.nandomiranda.themovies.model.api

import com.nandomiranda.themovies.model.api.movieJson.MovieJsonResponse
import com.nandomiranda.themovies.model.api.videoJson.VideoJsonResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    private companion object{
        private const val API_KEY= "5f460874d648ba2ac0b028bc36d0b079"
    }

    @GET("movie/popular?api_key=$API_KEY&language=es-MX")
    suspend fun getPopularMovie(): MovieJsonResponse

    @GET("movie/now_playing?api_key=$API_KEY&language=es-MX")
    suspend fun getPlayingMovie(): MovieJsonResponse

    @GET("movie/{id}/videos?api_key=$API_KEY&language=es-MX")
    suspend fun getVideoMovie(@Path("id") id : Int): VideoJsonResponse
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: MovieApiService = retrofit.create(MovieApiService::class.java)
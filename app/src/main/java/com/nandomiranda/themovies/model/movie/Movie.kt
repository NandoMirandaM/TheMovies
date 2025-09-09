package com.nandomiranda.themovies.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (val id : Int , val overview: String ,
                  val imageUrl: String, val title: String) :
    Parcelable
package com.nandomiranda.themovies.model.video

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoMovie (val id: Int, val url:String ) :
    Parcelable
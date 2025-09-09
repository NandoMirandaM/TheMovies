package com.nandomiranda.themovies.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.nandomiranda.themovies.R
import com.nandomiranda.themovies.databinding.ActivityMainBinding
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.view.interfaces.MovieSelectListener
import com.nandomiranda.themovies.view.fragments.MainViewPagerFragmentDirections

class MainActivity : AppCompatActivity(), MovieSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onMovieSelected(movie: Movie){
        findNavController(R.id.main_navigation_container)
            .navigate(MainViewPagerFragmentDirections.actionMainViewPagerFragmentToDetailFragment2(movie))
    }
}
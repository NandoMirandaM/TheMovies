package com.nandomiranda.themovies.view.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nandomiranda.themovies.databinding.FragmentDetailBinding
import com.nandomiranda.themovies.model.movie.Movie
import com.nandomiranda.themovies.viewModel.DetailViewModel
import com.nandomiranda.themovies.viewModel.DetailViewModelFactory


private const val IMAGE="https://image.tmdb.org/t/p/w500"
class DetailFragment : Fragment()  {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var image: ImageView
    private lateinit var trailer: VideoView
    private var url:String =""

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater , container , false)

        val movie = args.movie
        title = binding.titleText
        trailer = binding.movieVideo
        overview = binding.overviewText
        image = binding.posterImage
        setMovieData(movie)

        val viewModel=ViewModelProvider(this,DetailViewModelFactory(requireActivity().application,args.movie.id)).get(DetailViewModel::class.java)

        //observer para los cambios
        viewModel.videoList.observe(viewLifecycleOwner, Observer {
            url = it[0].url
            Log.e("Video" , "este es el contenido del arreglo en url = $url")
        })

        val mediaController = MediaController(context)
        mediaController.setAnchorView(trailer)

        val urlUri= Uri.parse("https://player.vimeo.com/video/282875052?h=6f092717f1")


        trailer.setVideoURI(urlUri)
        trailer.requestFocus()
        trailer.start()

        return binding.root
    }


    private fun setMovieData(movie: Movie)
    {
        Glide.with(this).load(IMAGE+movie.imageUrl).into(image)
        title.text = movie.title
        overview.text = movie.overview

    }


}
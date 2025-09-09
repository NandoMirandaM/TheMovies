package com.nandomiranda.themovies.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nandomiranda.themovies.databinding.FragmentMovieBinding
import com.nandomiranda.themovies.view.interfaces.MovieSelectListener
import com.nandomiranda.themovies.view.adapter.MovieAdapter
import com.nandomiranda.themovies.viewModel.MovieViewModel
import java.lang.ClassCastException

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    private lateinit var movieSelectListener: MovieSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieSelectListener = try {
            context as MovieSelectListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement MovieSelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container,false)

        //se crea el RecyclerView
        val recyclerView = binding.movieRecycler
        recyclerView.layoutManager= GridLayoutManager(requireActivity(),3)

        //ViewModel de ListFragment
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        //asignamos el adapter al recyclerview
        val adapter = MovieAdapter()
        binding.movieRecycler.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
                movieList ->
            adapter.submitList(movieList)
        })

        adapter.onItemClickListener={
            movieSelectListener.onMovieSelected(it)
            //Toast.makeText(context,"Click in Movie",Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}
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
import com.nandomiranda.themovies.databinding.FragmentPlayingBinding
import com.nandomiranda.themovies.view.interfaces.MovieSelectListener
import com.nandomiranda.themovies.view.adapter.MovieAdapter
import com.nandomiranda.themovies.viewModel.PlayingViewModel
import java.lang.ClassCastException

class PlayingFragment : Fragment() {
    private lateinit var binding: FragmentPlayingBinding
    private lateinit var playingSelectListener: MovieSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        playingSelectListener = try {
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
        binding = FragmentPlayingBinding.inflate(inflater, container,false)

        //se crea el RecyclerView
        val recyclerView = binding.playingRecycler
        recyclerView.layoutManager= GridLayoutManager(requireActivity(),3)

        //ViewModel de ListFragment
        val viewModel = ViewModelProvider(this).get(PlayingViewModel::class.java)

        //asignamos el adapter al recyclerview
        val adapter = MovieAdapter()
        binding.playingRecycler.adapter = adapter

        viewModel.playingList.observe(viewLifecycleOwner, Observer {
                playingList ->
            adapter.submitList(playingList)
        })

        adapter.onItemClickListener={
            playingSelectListener.onMovieSelected(it)
        }

        return binding.root
    }


}
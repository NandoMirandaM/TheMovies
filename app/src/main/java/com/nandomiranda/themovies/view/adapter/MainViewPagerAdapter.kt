package com.nandomiranda.themovies.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nandomiranda.themovies.view.fragments.MovieFragment
import com.nandomiranda.themovies.view.fragments.PlayingFragment

class MainViewPagerAdapter( fragments: FragmentActivity): FragmentStateAdapter(fragments) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { MovieFragment() }
            1 -> { PlayingFragment() }
            else -> MovieFragment()
        }
    }
}
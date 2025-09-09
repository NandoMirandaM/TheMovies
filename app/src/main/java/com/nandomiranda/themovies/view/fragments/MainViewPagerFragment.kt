package com.nandomiranda.themovies.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.nandomiranda.themovies.R
import com.nandomiranda.themovies.databinding.FragmentMainViewPagerBinding
import com.nandomiranda.themovies.view.adapter.MainViewPagerAdapter

class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainViewPagerBinding.inflate(inflater , container , false)

        val adapterPager = MainViewPagerAdapter(requireActivity())
        binding.pager.adapter = adapterPager

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.more_popular)
                }
                1 -> {
                    tab.text = getString(R.string.now_playing)
                }
            }
        }.attach()

        return binding.root
    }
}
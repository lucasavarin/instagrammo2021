package com.example.instagrammo.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.instagrammo.view.views.recycler.MonoRecyclerFragment
import com.example.instagrammo.view.views.recycler.GridRecyclerFragment

class PagerAdapter(fm: FragmentManager, private val numOfTabs: Int)  : FragmentPagerAdapter(fm, numOfTabs) {

    override fun getItem(position: Int): Fragment = when(position) {

        0 -> { GridRecyclerFragment.newInstance }
        1 -> { MonoRecyclerFragment.newInstance }

        else -> {
            MonoRecyclerFragment.newInstance }
    }

    override fun getCount(): Int = numOfTabs
}
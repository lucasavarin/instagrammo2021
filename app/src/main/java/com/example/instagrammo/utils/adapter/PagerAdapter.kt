package com.example.instagrammo.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.instagrammo.view.views.recycler.GridRecyclerFragment
import com.example.instagrammo.view.views.recycler.MonoRecyclerFragment

class PagerAdapter(fm: FragmentManager, val numOfTabs: Int)  : FragmentPagerAdapter(fm, numOfTabs) {

    override fun getItem(position: Int): Fragment = when(position) {

        0 -> { MonoRecyclerFragment.newInstance }
        1 -> { GridRecyclerFragment.newInstance }

        else -> {
            GridRecyclerFragment.newInstance }
    }

    override fun getCount(): Int = numOfTabs
}
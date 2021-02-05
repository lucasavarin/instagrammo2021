package com.example.instagrammo.views

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.instagrammo.R
import com.example.instagrammo.recyclerview.adapter.OnPostItemClickListener
import com.example.instagrammo.views.follow.FollowFragment
import com.example.instagrammo.views.home.HomeFragment
import com.example.instagrammo.views.search.SearchFragment
import kotlinx.android.synthetic.main.activity_basehome.*
import com.example.instagrammo.views.profile.ProfileFragment

class BaseHomeActivity : BaseActivity(), OnPostItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basehome)

        addFragment(HomeFragment.homeFragment, R.id.fragment_container)

        bottomMenuNavigationManager()

    }

    fun bottomMenuNavigationManager(){

        bottom_nav.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.nav_home ->  {
                    replaceFragment(HomeFragment.homeFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_search -> {
                    replaceFragment(SearchFragment.searchFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_add -> {true}
                R.id.nav_follow -> {
                    replaceFragment(FollowFragment.followFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_profile -> {true}
                else -> false
            }
        }
    }

    override fun onPictureProfileItemListener(string: String) {
        Log.i("Ciao",string)
    }

}
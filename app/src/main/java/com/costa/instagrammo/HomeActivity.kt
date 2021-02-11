package com.costa.instagrammo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.adapter.PostAdapter
import com.costa.views.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //bottom_nav.background = null

        //followers

        //val posts:ArrayList<String> = ArrayList()

        //for (i in 1..10) {

        //    posts.add("Utente # $i")
        //}

        //recycler_view_orizzontale.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        //recycler_view_orizzontale.adapter = PostAdapter(posts)


        lateinit var homeFragment: HomeFragment
        lateinit var searchFragment: SearchFragment
        lateinit var addFragment: AddFragment
        lateinit var likeFragment: LikeFragment
        lateinit var profileFragment: ProfileFragment

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)

        homeFragment = HomeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.home_container_fragment, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.ic_home -> {

                    homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container_fragment, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.ic_search -> {

                    searchFragment = SearchFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container_fragment, searchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.ic_add -> {

                    addFragment = AddFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container_fragment, addFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.ic_like -> {

                    likeFragment = LikeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container_fragment, likeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.ic_profile -> {

                    profileFragment = ProfileFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container_fragment, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
            }

            true


        }
    }
}


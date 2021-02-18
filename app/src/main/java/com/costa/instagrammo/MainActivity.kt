package com.costa.instagrammo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.costa.beans.ProfileOut
import com.costa.views.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnClickListener{
            bottom_nav.isSelected = !bottom_nav.isSelected
        }
        /*  bottom_nav.background = null  */

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

    override fun modifyProfilePressed(profile: ProfileOut) {
        var editProfileFragment = EditProfileFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.home_container_fragment, editProfileFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

}

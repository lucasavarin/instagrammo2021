package com.example.instagrammo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.*
import com.example.instagrammo.fragment.*
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val addFragment = AddFragment()
    val favouritesFragment = FavouritesFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

addFragment(homeFragment, R.id.fragment_container)


        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(homeFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_search -> {

                    replaceFragment(searchFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_add -> {

                    replaceFragment(addFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_favourites -> {

                    replaceFragment(favouritesFragment, R.id.fragment_container)
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(profileFragment, R.id.fragment_container)
                    true
                }
                else ->  false
            }
        }

        }


    }

    fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }



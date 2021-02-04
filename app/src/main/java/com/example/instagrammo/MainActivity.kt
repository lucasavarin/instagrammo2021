package com.example.instagrammo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val addFragment = AddFragment()
        val favouritesFragment = FavouritesFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFagment(homeFragment)

        bottom_navigation.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> makeCurrentFagment(homeFragment)
                R.id.nav_search -> makeCurrentFagment(searchFragment)
                R.id.nav_add -> makeCurrentFagment(addFragment)
                R.id.nav_favourites -> makeCurrentFagment(favouritesFragment)
                R.id.nav_profile -> makeCurrentFagment(profileFragment)
            }
        }

    }

    private fun makeCurrentFagment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
}
package com.costa.instagrammo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.costa.views.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_nav.background = null

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val addFragment = AddFragment()
        val likeFragment = LikeFragment()
        val profileFragment = ProfileFragment()

        esecuzioneFagment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener() {
        when (it.itemId){
            R.id.ic_home -> {esecuzioneFagment(homeFragment)
                true}
            R.id.ic_search -> {esecuzioneFagment(searchFragment)
                true}
            R.id.ic_add -> {esecuzioneFagment(addFragment)
                true}
            R.id.ic_like -> {esecuzioneFagment(likeFragment)
                true}
            R.id.ic_profile -> {esecuzioneFagment(profileFragment)
                true}
            else -> false
        }
    }
}

private fun esecuzioneFagment(fragment: Fragment) =
    supportFragmentManager.beginTransaction().apply{
        replace(R.id.home_container_fragment, fragment)
        commit()
    }
}
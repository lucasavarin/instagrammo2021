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

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val addFragment = AddFragment()
        val likeFragment = LikeFragment()
        val profileFragment = ProfileFragment()

        esecuzioneFagment(homeFragment)
        bottom_nav.setOnNavigationItemReselectedListener {
        when (it.itemId){
            R.id.ic_home -> esecuzioneFagment(homeFragment)
            R.id.ic_search -> esecuzioneFagment(searchFragment)
            R.id.ic_add -> esecuzioneFagment(addFragment)
            R.id.ic_like -> esecuzioneFagment(likeFragment)
            R.id.ic_profile -> esecuzioneFagment(profileFragment)
        }
    }
}

private fun esecuzioneFagment(fragment: Fragment) =
    supportFragmentManager.beginTransaction().apply{
        replace(R.id.layout_home_activity, fragment)
        commit()
    }
}
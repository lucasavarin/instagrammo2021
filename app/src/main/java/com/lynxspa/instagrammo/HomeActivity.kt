 package com.lynxspa.instagrammo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragment.*
import singleton.ApiClient
import javax.security.auth.callback.Callback

 class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


       /*.onNavigationItemSelectedListener {
            toHome()
        }
        searchButton.onNavigationItemSelectedListener {
            toSearch()
        }
        addButton.onNavigationItemSelectedListener {
            toAdd()
        }
        followButton.onNavigationItemSelectedListener {
            toFollow()
        }
        profileButton.onNavigationItemSelectedListener {
            toProfile()
        }*/



    }
     fun toHome(){
         val homeFragment = HomeFragment.makeIstance()

         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.activityHome, homeFragment)
         fragmentTransaction.commit()

     }

     fun toSearch{
         val searchFragment = SearchFragment.makeIstance()

         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.activityHome, searchFragment)
         fragmentTransaction.commit()

     }

     fun toAdd(){
         val addFragment = AddFragment.makeIstance()

         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.activityHome, addFragment)
         fragmentTransaction.commit()

     }

     fun toFollow(){
         val followFragment = FollowFragment.makeIstance()

         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.activityHome, followFragment)
         fragmentTransaction.commit()

     }

     fun toProfile(){
         val profileFragment = ProfileFragment.makeIstance()

         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.add(R.id.activityHome, profileFragment)
         fragmentTransaction.commit()

     }


 }
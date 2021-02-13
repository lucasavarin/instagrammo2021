package com.lynx.instagrammo.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.*
import com.lynx.instagrammo.addFragment
import com.lynx.instagrammo.view.add.AddFragment
import com.lynx.instagrammo.view.home.HomeFragment
import com.lynx.instagrammo.view.notification.NotificationsFragment
import com.lynx.instagrammo.view.profile.ProfileFragment
import com.lynx.instagrammo.view.search.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*


/* toDO cambiare il nome dell'activity in BaseActivity */
class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val addFragment = AddFragment()
    val notificationsFragment = NotificationsFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /* ToDO change to home frament */
        // loadPosts()
        addFragment(homeFragment, R.id.fragmentConainer)

        val username = prefs.username
        Log.i("INFORMATION", username)


        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    replaceFragment(homeFragment, R.id.fragmentConainer)
                    true
                }
                R.id.action_search -> {
                    replaceFragment(searchFragment, R.id.fragmentConainer)
                    true
                }
                R.id.action_add -> {
                    replaceFragment(addFragment, R.id.fragmentConainer)
                    true
                }
                R.id.action_love -> {
                    replaceFragment(notificationsFragment, R.id.fragmentConainer)
                    true
                }
                R.id.action_profile -> {
                    replaceFragment(profileFragment, R.id.fragmentConainer)
                    true
                }
                else -> false
            }
        }
    }


}
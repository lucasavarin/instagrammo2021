package com.lynx.instagrammo.Home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.lynx.instagrammo.Fragment.*
import com.lynx.instagrammo.R
import com.lynx.instagrammo.prefs
import kotlinx.android.synthetic.main.activity_home.*


/* toDO cambiare il nome dell'activity in BaseActivity */
class HomeActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val addFragment = AddFragment()
    val notificationsFragment = NotificationsFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /* ToDO change to home frament */
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

    /*toDO classe di gestione fragment*/
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

}
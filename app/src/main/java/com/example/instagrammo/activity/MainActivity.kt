package com.example.instagrammo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
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

        addFragment(homeFragment,R.id.fragment_container)

        navigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        navigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_search))
        navigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_add))
        navigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_favorite))
        navigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_person))

        navigation.setOnClickMenuListener {
            when (it.id) {
                0 -> {
                   replaceFragment(homeFragment,R.id.fragment_container)

                }
                1 -> {
                    replaceFragment(searchFragment,R.id.fragment_container)

                }
                2 -> {
                    replaceFragment(addFragment,R.id.fragment_container)

                }
                3 -> {
                    replaceFragment(favouritesFragment,R.id.fragment_container)

                }
                4 -> {
                    replaceFragment(profileFragment,R.id.fragment_container)

                }
                else -> false

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

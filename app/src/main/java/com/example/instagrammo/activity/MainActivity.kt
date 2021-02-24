package com.example.instagrammo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.instagrammo.fragment.*
import com.example.instagrammo.fragment.secondFragment.ModifyProfileFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() , InterfaceApp{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        navigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_search))
        navigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_add))
        navigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_favorite))
        navigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_person))

        addFragment(HomeFragment(), R.id.fragment_container)

        navigation.setOnClickMenuListener {
            when (it.id) {
                0 -> replaceFragment(HomeFragment(), R.id.fragment_container)
                1 -> replaceFragment(SearchFragment(), R.id.fragment_container)
                2 -> replaceFragment(AddFragment(), R.id.fragment_container)
                3 -> replaceFragment(FavouritesFragment(), R.id.fragment_container)
                4 -> replaceFragment(ProfileFragment(), R.id.fragment_container)
                else -> false

            }
        }
    }

    override fun backToProfile() {
       supportFragmentManager.beginTransaction().apply {
           remove(ModifyProfileFragment.newInstance)
           commit()
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

package com.costa.instagrammo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.costa.beans.ProfileOut
import com.costa.utils.addFragment
import com.costa.utils.removeFragment
import com.costa.utils.replaceFragment
import com.costa.views.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface,
    EditProfileFragment.EditProfileFragmentInterface,AddFragment.AddFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnClickListener {
            bottom_nav.isSelected = !bottom_nav.isSelected
        }

        /*  bottom_nav.background = null  */


        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)


        replaceFragment(HomeFragment.instance, R.id.home_container_fragment)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.ic_home -> {

                    replaceFragment(HomeFragment.instance, R.id.home_container_fragment)
                }

                R.id.ic_search -> {

                    replaceFragment(SearchFragment.instance, R.id.home_container_fragment)
                }

                R.id.ic_add -> {

                    replaceFragment(AddFragment.instance, R.id.home_container_fragment)
                }

                R.id.ic_like -> {

                    replaceFragment(LikeFragment.instance, R.id.home_container_fragment)
                }

                R.id.ic_profile -> {

                    replaceFragment(ProfileFragment.instance, R.id.home_container_fragment)
                }
            }

            true

        }

    }

    override fun modifyProfilePressed(profile: ProfileOut) {
        var bundle = Bundle()
        bundle.putString("nome", profile.name)
        bundle.putString("description", profile.description)
        bundle.putString("picture", profile.picture)

        EditProfileFragment.instance.arguments = bundle

        addFragment(EditProfileFragment.instance, R.id.home_container_fragment)
        removeFragment(ProfileFragment.instance)
    }

    override fun back() {
        replaceFragment(ProfileFragment.instance, R.id.home_container_fragment)
        removeFragment(EditProfileFragment.instance)

    }

    override fun onClickImage(profile: ProfileOut) {
      //TODO: da implementare
    }

}

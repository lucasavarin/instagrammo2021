package com.costa.instagrammo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.costa.beans.business.PicSumImage
import com.costa.beans.rest.ProfileOut
import com.costa.utils.addFragment
import com.costa.utils.removeFragment
import com.costa.utils.replaceFragment
import com.costa.views.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface,
    EditProfileFragment.EditProfileFragmentInterface, AddFragment.AddFragmentInterface,
    AddPostStep1Fragment.AddPostStep1Interface, AddPostStep2Fragment.AddPostStep2Interface {
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

    override fun onClickImage(imageOut: PicSumImage) {

        addFragment(AddPostStep1Fragment.getinstance(imageOut), R.id.home_container_fragment)
        removeFragment(AddFragment.instance)
    }

    override fun backToAddFragment() {
        replaceFragment(AddFragment.instance, R.id.home_container_fragment)
        removeFragment(AddPostStep1Fragment.instance)
    }

    override fun addPostStep1ToStep2(image: PicSumImage) {
        addFragment(AddPostStep2Fragment.getinstance(image), R.id.home_container_fragment)
        removeFragment(AddPostStep1Fragment.instance)
    }


    override fun salvaPost() {
        replaceFragment(AddFragment.instance, R.id.home_container_fragment)
        removeFragment(AddPostStep2Fragment.instance)
    }

    override fun backToAddPostStep1() {
        replaceFragment(AddPostStep1Fragment.instance, R.id.home_container_fragment)
        removeFragment(AddPostStep2Fragment.instance)
    }
}

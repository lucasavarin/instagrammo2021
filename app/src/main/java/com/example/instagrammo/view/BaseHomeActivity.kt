package com.example.instagrammo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.R
import com.example.instagrammo.recyclerview.adapter.OnPostItemClickListener
import com.example.instagrammo.view.views.follow.FollowFragment
import com.example.instagrammo.view.views.home.HomeFragment
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import com.example.instagrammo.view.views.profile.EditProfileFragment
import com.example.instagrammo.view.views.profile.EditProfileFragmentListener
import com.example.instagrammo.view.views.search.SearchFragment
import kotlinx.android.synthetic.main.activity_basehome.*
import com.example.instagrammo.view.views.profile.ProfileFragment

class BaseHomeActivity : BaseActivity(), OnPostItemClickListener, ButtonEditProfileListener, EditProfileFragmentListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basehome)

        addFragment(HomeFragment.newInstance, R.id.fragment_container)

        bottomMenuNavigationManager()

    }

    private fun bottomMenuNavigationManager(){

        bottom_nav.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.nav_home ->  {
                    replaceFragment(HomeFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_search -> {
                    replaceFragment(SearchFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_add -> {
                    true
                }
                R.id.nav_follow -> {
                    replaceFragment(FollowFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
                    true
                }
                else -> false
            }
        }
    }

    override fun onPictureProfileItemListener(string: String) {
        Log.i("Ciao",string)
    }

    //bottone per aprire edit profile
    override fun OnButtonPressedListener(pressed: Boolean) {
        //replaceFragment(EditProfileFragment.newInstance, R.id.fragment_container)
        addFragment(EditProfileFragment.newInstance, R.id.fragment_container)
        removeFragment(ProfileFragment.newInstance)
    }

    //rimuove fragment modifica profilo e apre profilo
    override fun removeFragmentListener() {
        replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
        removeFragment(EditProfileFragment.newInstance)
    }
}
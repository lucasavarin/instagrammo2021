package com.example.instagrammo.views

import android.os.Bundle
import android.util.Log
import com.example.instagrammo.R
import com.example.instagrammo.recyclerview.adapter.OnPostItemClickListener
import com.example.instagrammo.views.follow.FollowFragment
import com.example.instagrammo.views.home.HomeFragment
import com.example.instagrammo.views.profile.ButtonEditProfileListener
import com.example.instagrammo.views.profile.EditProfileFragment
import com.example.instagrammo.views.profile.EditProfileFragmentListener
import com.example.instagrammo.views.search.SearchFragment
import kotlinx.android.synthetic.main.activity_basehome.*
import com.example.instagrammo.views.profile.ProfileFragment

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
        replaceFragment(EditProfileFragment.newInstance, R.id.fragment_container)
    }

    //rimuove fragment modifica profilo e apre profilo
    override fun removeFragmentListener() {
        replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
    }


}
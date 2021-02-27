package com.example.instagrammo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.service.NotificationService
import com.example.instagrammo.view.views.add.OnImageItemClickListener
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.example.instagrammo.view.views.add.AddFragment
import com.example.instagrammo.view.views.add.AddPostConfirmFragment
import com.example.instagrammo.view.views.add.AddPostFragment
import com.example.instagrammo.view.views.follow.FollowFragment
import com.example.instagrammo.view.views.home.HomeFragment
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import com.example.instagrammo.view.views.profile.EditProfileFragment
import com.example.instagrammo.view.views.profile.EditProfileFragmentListener
import com.example.instagrammo.view.views.search.SearchFragment
import kotlinx.android.synthetic.main.activity_basehome.*
import com.example.instagrammo.view.views.profile.ProfileFragment
import com.example.instagrammo.views.BaseActivity

class BaseHomeActivity : BaseActivity(),
    OnPostItemClickListener, ButtonEditProfileListener, EditProfileFragmentListener,
    OnImageItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basehome)
        addFragment(HomeFragment.newInstance, R.id.fragment_container)

        startNotificationService()

        bottomMenuNavigationManager()
        Handler(Looper.getMainLooper()).postDelayed({
            //getdata()
        }, 5000)
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
                    replaceFragment(AddFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_follow -> {
                    replaceFragment(FollowFragment.newInstance, R.id.fragment_container)
                    stopNotificationService()
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
        //removeFragment(ProfileFragment.newInstance)
    }

    //rimuove fragment modifica profilo e apre profilo
    override fun removeFragmentListener(fragment: Fragment) {
        //replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
        removeFragment(fragment)
    }

    override fun onImageItemAddListener(image: LoremBean) {
        val fragment = AddPostConfirmFragment.newInstance(image)
        replaceFragment(fragment, R.id.fragment_container)
        //addFragment(fragment, R.id.fragment_container)

    }

    override fun onImageItemAddPostListener(image: LoremBean) {
        val fragment = AddPostFragment.newInstance(image)
        addFragment(fragment, R.id.fragment_container)
    }


    private fun startNotificationService() {
        startService(Intent(this, NotificationService.newInstance::class.java))
    }

    private fun stopNotificationService() {
        stopService(Intent(this, NotificationService.newInstance::class.java))
    }


}
package com.lynx.instagrammo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.*
import com.lynx.instagrammo.addFragment
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.view.add.AddFragment
import com.lynx.instagrammo.view.add.AddPostFragment
import com.lynx.instagrammo.view.add.ShowImageFragment
import com.lynx.instagrammo.view.edit.EditFragment
import com.lynx.instagrammo.view.followerDetail.FollowerDetailFragment
import com.lynx.instagrammo.view.home.HomeFragment
import com.lynx.instagrammo.view.notification.NotificationsFragment
import com.lynx.instagrammo.view.profile.ProfileFragment
import com.lynx.instagrammo.view.search.SearchFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_follower_detail.*


/* toDO cambiare il nome dell'activity in BaseActivity */
class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface, EditFragment.EditFragmenInterface, HomeFragment.HomeFragmentInterface , AddFragment.AddFragmentInterface, ShowImageFragment.ShowImageFragmentInterface, AddPostFragment.AddPostFragmentInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        for(i in 0 until navView.menu.size()){
        navView.itemIconTintList = null
        }

        /* ToDO change to home frament */

        addFragment(HomeFragment.newInstance, R.id.fragmentConainer)

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    replaceFragment(HomeFragment.newInstance, R.id.fragmentConainer)
                    true
                }
                R.id.action_search -> {
                    replaceFragment(SearchFragment.newInstance, R.id.fragmentConainer)
                    true
                }
                R.id.action_add -> {
                    replaceFragment(AddFragment.newInstance, R.id.fragmentConainer)
                    true
                }
                R.id.action_love -> {
                    replaceFragment(NotificationsFragment.newInstance, R.id.fragmentConainer)
                    true
                }
                R.id.action_profile -> {
                    replaceFragment(ProfileFragment.newInstance, R.id.fragmentConainer)
                    true
                }
                else -> false
            }
        }
    }

    override fun modifyProfilePressed(profile: Profile) {
        addFragment(EditFragment.newInstance, R.id.fragmentConainer)
        removeFragment(ProfileFragment.newInstance)
    }

    override fun saveAndExit() {
        replaceFragment(ProfileFragment.newInstance, R.id.fragmentConainer)
        removeFragment(EditFragment.newInstance)
    }

    override fun backToProfile() {
        replaceFragment(ProfileFragment.newInstance, R.id.fragmentConainer)
        removeFragment(EditFragment.newInstance)
    }

    override fun goToProfilepressed(follower: Follower) {
        FollowerDetailFragment.newInstance.getFollow(follower)
        replaceFragment(FollowerDetailFragment.newInstance, R.id.fragmentConainer)

    }

    override fun goToAddImage(item: PicsumImageRest) {
        replaceFragment(ShowImageFragment.newInstance(item), R.id.fragmentConainer)
    }

    override fun goToAddPost(item: PicsumImageRest) {
        addFragment(AddPostFragment.newInstace(item), R.id.fragmentConainer)
    }

    override fun addPostAndExit() {
        replaceFragment(HomeFragment.newInstance, R.id.fragmentConainer)
        removeFragment(ShowImageFragment.newInstance)
    }

    override fun backToAdd() {
        replaceFragment(AddFragment.newInstance, R.id.fragmentConainer)
        removeFragment(ShowImageFragment.newInstance)
    }

    override fun backToShowImage() {
        replaceFragment(ShowImageFragment.newInstance, R.id.fragmentConainer)
        removeFragment(AddPostFragment.newInstance)
    }

}
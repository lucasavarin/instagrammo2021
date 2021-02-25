package com.lynx.instagrammo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.*
import com.lynx.instagrammo.addFragment
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.NumberPostsResponse
import com.lynx.instagrammo.view.add.AddFragment
import com.lynx.instagrammo.view.add.AddPostFragment
import com.lynx.instagrammo.view.add.ShowImageFragment
import com.lynx.instagrammo.view.edit.EditFragment
import com.lynx.instagrammo.view.followerDetail.FollowerDetailFragment
import com.lynx.instagrammo.view.home.HomeFragment
import com.lynx.instagrammo.view.notification.NotificationsFragment
import com.lynx.instagrammo.view.profile.ProfileFragment
import com.lynx.instagrammo.view.search.SearchFragment
import com.lynx.instagrammo.view.service.ForegroundService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_follower_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/* toDO cambiare il nome dell'activity in BaseActivity */
class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface, EditFragment.EditFragmenInterface, HomeFragment.HomeFragmentInterface , AddFragment.AddFragmentInterface, ShowImageFragment.ShowImageFragmentInterface, AddPostFragment.AddPostFragmentInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        for(i in 0 until navView.menu.size()){
        navView.itemIconTintList = null
        }

        addFragment(HomeFragment.newInstance, R.id.fragmentConainer)
        getPostDifference()
        
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
                    ForegroundService.stopService(this)
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
        addFragment(AddPostFragment.newInstace(item ), R.id.fragmentConainer)
    }

    override fun addPostAndExit() {
        replaceFragment(HomeFragment.newInstance, R.id.fragmentConainer)
        removeFragment(ShowImageFragment.newInstance)
    }


    private fun getPostDifference() {
        var handler = Handler()
        var runnable: Runnable? = null
        var delay = 5000

        /*todo sostituire queste variabili con dei prefs*/
        var newPostsNumber = 0
        var oldPostsNumber = 0

        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())


            var difference = newPostsNumber.compareTo(oldPostsNumber)

            ApiClient.GetClient.getNumberPost().enqueue(object : Callback<NumberPostsResponse> {
                override fun onResponse(call: Call<NumberPostsResponse>, response: Response<NumberPostsResponse>) {
                        oldPostsNumber = newPostsNumber
                        newPostsNumber = response.body()!!.payload!!.toInt()
                }

                override fun onFailure(call: Call<NumberPostsResponse>, t: Throwable) {

                }

            })
            Log.i("OLD NUMBER :", "$oldPostsNumber")
            Log.i("NEW NUMBER :", "$newPostsNumber")
        Log.i("DIFFERENCE :", "$difference")
         if(difference > 0){
            var input = newPostsNumber - oldPostsNumber
             ForegroundService.startService(this, input.toString())
         }

        }.also { runnable = it }, delay.toLong())

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
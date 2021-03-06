package com.costa.views.main

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.costa.beans.business.PicSumImage
import com.costa.beans.rest.ProfileOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.NumberPostsResponse
import com.costa.utils.addFragment
import com.costa.utils.prefs
import com.costa.utils.removeFragment
import com.costa.utils.replaceFragment
import com.costa.views.main.addpost.AddFragment
import com.costa.views.main.addpost.AddPostStep1Fragment
import com.costa.views.main.addpost.AddPostStep2Fragment
import com.costa.views.main.home.HomeFragment
import com.costa.views.main.like.LikeFragment
import com.costa.views.main.profile.EditProfileFragment
import com.costa.views.main.profile.ProfileFragment
import com.costa.views.main.search.SearchFragment
import com.costa.views.service.ForegroundService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Observer


class MainActivity : AppCompatActivity(), ProfileFragment.ProfileFragmentInterface,
    EditProfileFragment.EditProfileFragmentInterface, AddFragment.AddFragmentInterface,
    AddPostStep1Fragment.AddPostStep1Interface, AddPostStep2Fragment.AddPostStep2Interface {

    //private var progressDialog : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnClickListener {
            bottom_nav.isSelected = !bottom_nav.isSelected
            /*bottom_nav.visibility = View.GONE
            showProgress()
            Handler().postDelayed({
                hideProgress()
                bottom_nav.visibility = View.VISIBLE
            }, 5000)*/
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)


        replaceFragment(
            HomeFragment.instance,
            R.id.home_container_fragment
        )

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.ic_home -> {

                    replaceFragment(
                        HomeFragment.instance,
                        R.id.home_container_fragment
                    )
                }

                R.id.ic_search -> {

                    replaceFragment(
                        SearchFragment.instance,
                        R.id.home_container_fragment
                    )
                }

                R.id.ic_add -> {

                    replaceFragment(
                        AddFragment.instance,
                        R.id.home_container_fragment
                    )
                }

                R.id.ic_like -> {
                    bottomNavigation.getOrCreateBadge(R.id.ic_like).apply{
                        number =0
                        isVisible= false
                    }
                    ForegroundService.startService(this, "Non ci sono post nuovi")

                    replaceFragment(
                        LikeFragment.instance,
                        R.id.home_container_fragment
                    )
                }

                R.id.ic_profile -> {

                    replaceFragment(
                        ProfileFragment.instance,
                        R.id.home_container_fragment
                    )
                }
            }

            true

        }
            val handler = Handler()
            var runnable: Runnable?=null
            handler.postDelayed(Runnable{

                handler.postDelayed(runnable, 5000.toLong())

                ApiClient.getClient.getPostsNumber().enqueue(object : Callback<NumberPostsResponse>{
                    override fun onFailure(call: Call<NumberPostsResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<NumberPostsResponse>,
                        response: Response<NumberPostsResponse>
                    ) {
                        if(!(prefs.postVisualizzati.compareTo(response.body()!!.payload!!).equals(0))){

                            var dif= response.body()!!.payload?.minus(prefs.postVisualizzati)
                            bottomNavigation.getOrCreateBadge(R.id.ic_like).apply{
                                number =  dif!!
                                isVisible= true
                            }
                            ForegroundService.startService(this@MainActivity, "Post Nuovi :$dif")
                            prefs.postNews=response.body()!!.payload!!
                        }
                    }
                })
            }.also { runnable=it }, 5000.toLong())


        /*val loadingDialogFragment by lazy { LoadingDialogFragment() }

        //Show Loader
        if (!loadingDialogFragment.isAdded){
            loadingDialogFragment.show(supportFragmentManager, "loader")
        }

        //Hide Loader
        if (loadingDialogFragment.isAdded) {
            loadingDialogFragment.dismissAllowingStateLoss()
        }*/



    }

    override fun modifyProfilePressed(profile: ProfileOut) {
        var bundle = Bundle()
        bundle.putString("nome", profile.name)
        bundle.putString("description", profile.description)
        bundle.putString("picture", profile.picture)

        EditProfileFragment.instance.arguments = bundle

        addFragment(
            EditProfileFragment.instance,
            R.id.home_container_fragment
        )
        removeFragment(ProfileFragment.instance)
    }

    override fun back() {
        replaceFragment(
            ProfileFragment.instance,
            R.id.home_container_fragment
        )
        removeFragment(EditProfileFragment.instance)

    }

    override fun onClickImage(imageOut: PicSumImage) {

        addFragment(
            AddPostStep1Fragment.getinstance(imageOut),
            R.id.home_container_fragment
        )
        removeFragment(AddFragment.instance)
    }

    override fun backToAddFragment() {
        replaceFragment(
            AddFragment.instance,
            R.id.home_container_fragment
        )
        removeFragment(AddPostStep1Fragment.instance)
    }

    override fun addPostStep1ToStep2(image: PicSumImage) {
        addFragment(
            AddPostStep2Fragment.getinstance(image),
            R.id.home_container_fragment
        )
        removeFragment(AddPostStep1Fragment.instance)
    }


    override fun salvaPost() {
        replaceFragment(
            AddFragment.instance,
            R.id.home_container_fragment
        )
        removeFragment(AddPostStep2Fragment.instance)
    }

    override fun backToAddPostStep1() {
        replaceFragment(
            AddPostStep1Fragment.instance,
            R.id.home_container_fragment
        )
        removeFragment(AddPostStep2Fragment.instance)
    }




}

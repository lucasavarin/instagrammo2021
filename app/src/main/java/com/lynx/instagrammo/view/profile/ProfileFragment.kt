package com.lynx.instagrammo.view.profile

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.lynx.instagrammo.R
import com.lynx.instagrammo.addFragment
import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.inTransaction
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.MyPostsResponse
import com.lynx.instagrammo.networking.ProfileResponse
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.CircleTransform
import com.lynx.instagrammo.view.MainActivity
import com.lynx.instagrammo.view.edit.EditFragment
import com.lynx.instagrammo.view.recyclerView.ProfileGridAdapter
import com.lynx.instagrammo.view.recyclerView.ProfileListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var listener: ProfileFragmentInterface
    private lateinit var profile: Profile
    var posts: List<MyPost> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ProfileFragmentInterface){
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        edit_profile_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            listener.modifyProfilePressed(profile)
            }
        })

        tab_button_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    recycler_profileImg_grid.visibility = View.VISIBLE
                } else if (tab!!.position == 1) {
                    recycler_profileImg_list.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    recycler_profileImg_grid.visibility = View.GONE
                } else if (tab!!.position == 1) {
                    recycler_profileImg_list.visibility = View.GONE
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        callGetSingleProfile()
        callGetSinglePost()
    }


    private fun callGetSingleProfile() {
        ApiClient.GetClient.getSingleProfile(prefs.userId).enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                if (response.isSuccessful) {
                    profile = response.body()!!.payload!![0]
                    profile_name_text.text = response.body()!!.payload!![0].name
                    profile_description_text.text = response.body()!!.payload!![0].description
                    followers_count_text.text = response.body()!!.payload!![0].followersNumber
                    posts_count_text.text = response.body()!!.payload!![0].postsNumber
                    Picasso.get().load(response.body()!!.payload!![0].picture).transform(CircleTransform()).into(profile_image)
                }

            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.i("ONFALIURE", t.message)
            }
        })
    }


    private fun callGetSinglePost() {
        ApiClient.GetClient.getMyPosts(prefs.userId).enqueue(object : Callback<MyPostsResponse> {
            override fun onResponse(call: Call<MyPostsResponse>, response: Response<MyPostsResponse>) {
                posts = response.body()!!.payload!!
                gridLayoutManager(response.body()!!.payload!!)
                linearLayoutManager(response.body()!!.payload!!)
            }

            override fun onFailure(call: Call<MyPostsResponse>, t: Throwable) {

            }

        })
    }


    private fun gridLayoutManager(payload: List<MyPost>) {
        recycler_profileImg_grid.apply {
            val gridLayoutManager = GridLayoutManager(context, 3)
            recycler_profileImg_grid.layoutManager = gridLayoutManager
            recycler_profileImg_grid.adapter = ProfileGridAdapter(payload)
        }
    }

    private fun linearLayoutManager(payload: List<MyPost>) {
        recycler_profileImg_list.visibility = View.GONE
        recycler_profileImg_list.apply {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycler_profileImg_list.layoutManager = linearLayoutManager
            recycler_profileImg_list.adapter = ProfileListAdapter(payload)
        }
    }

    interface ProfileFragmentInterface{
        fun modifyProfilePressed(profile: Profile)
    }

    companion object {
        val newInstance: ProfileFragment = ProfileFragment()
    }
}


package com.example.instagrammo.fragment


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.instagrammo.R
import com.example.instagrammo.adapter.PostListGridAdapter
import com.example.instagrammo.bean.Post
import com.example.instagrammo.bean.Profile
import com.example.instagrammo.fragment.secondFragment.ModifyProfileFragment
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.FollowerResponse
import com.example.instagrammo.response.PayloadProfile
import com.example.instagrammo.response.PostResponse
import com.example.instagrammo.response.ProfileResponse
import com.example.instagrammo.view.CircleTransformation
import com.example.instagrammo.view.prefs
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    private var profile: List<PayloadProfile> = mutableListOf()
    private var posts: List<Post> = mutableListOf()
    val modifyProfile = ModifyProfileFragment()

    val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    val gridLayoutManager = GridLayoutManager(context, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInformationProfile()
        getPostsProfile()
        recycler_profile_grid.layoutManager = gridLayoutManager
        recycler_profile.layoutManager = linearLayoutManager
        edit_btn.setOnClickListener {
            val fragmentManager: FragmentManager? = fragmentManager
            var transaction: FragmentTransaction = fragmentManager?.beginTransaction()!!
            transaction.replace(R.id.fragment_container, modifyProfile)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.i("info", tab.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.i("info", tab.toString())

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            recycler_profile.visibility = View.GONE
                            recycler_profile_grid.visibility = View.VISIBLE
                        }
                        1 -> {
                            recycler_profile_grid.visibility = View.GONE
                            recycler_profile.visibility = View.VISIBLE

                        }

                    }
                }
            }

        })

    }

    fun getPostsProfile() {
        ApiClient.getClient.getProfilePosts(prefs.idProfilo)
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.i("info", t.message)
                }

                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if (!response.body()?.payload.isNullOrEmpty()) {
                        posts = response.body()?.payload?.toMutableList()!!
                        recycler_profile_grid.adapter = PostListGridAdapter(posts)

                        recycler_profile.adapter = PostListGridAdapter(posts)
                        Log.i("info ", response.body()?.result.toString())
                    }
                }

            })
    }

    fun getInformationProfile() {
        ApiClient.getClient.getSingleProfile(prefs!!.idProfilo)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("info", t.message)
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        profile = response.body()?.payload!!
                        Picasso.get().load(profile[0].picture).transform(CircleTransformation())
                            .into(user_image)
                        profile_name.text = profile[0].name
                        profile_description.text = profile[0].description
                        if (profile[0].postNumber.isNullOrBlank()) {
                            profile[0].postNumber = "0"
                        }
                        if (profile[0].followersNumber.isNullOrBlank()) {
                            profile[0].followersNumber = "0"
                        }
                        number_post.text = profile[0].postNumber
                        number_follower.text = profile[0].followersNumber
                    }
                    Log.i("info", response.body()?.result.toString())
                }

            })

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}

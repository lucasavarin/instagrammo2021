package com.costa.views.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.adapter.homePostsList.PostAdapter
import com.costa.adapter.homeFollowersList.FollowersListAdapter
import com.costa.beans.converter.FollowerConverter
import com.costa.beans.converter.PostConverter
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.FollowersResponse
import com.costa.servizi.PostsResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    companion object{
        val instance: HomeFragment =
            HomeFragment()

    }
    lateinit var callFollowers:Call<FollowersResponse>
    lateinit var callPosts:Call<PostsResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        followers()
        posts()
    }

    override fun onDestroy() {
        callFollowers.cancel()
        callPosts.cancel()
        super.onDestroy()
    }


    fun followers(){
        callFollowers=ApiClient.getClient.getFollowes(userId)
        callFollowers.enqueue(object : Callback<FollowersResponse> {

            override fun onResponse(call: Call<FollowersResponse>, response: Response<FollowersResponse>) {
                if (!response.body()!!.payload!!.isNullOrEmpty()) {


                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recycleHorizontal.layoutManager = layoutManager
                    recycleHorizontal.adapter =
                        FollowersListAdapter(
                            FollowerConverter.restToBusiness(response.body()!!.payload!!)
                        )
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {

            }


        })
    }
    fun posts(){
        callPosts= ApiClient.getClient.getPost()
        callPosts.enqueue(object : Callback<PostsResponse> {

            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if (!response.body()!!.payload!!.isNullOrEmpty()) {


                    val layoutManagerPosts = LinearLayoutManager(context)
                    rv_post.layoutManager = layoutManagerPosts
                    rv_post.adapter =
                        PostAdapter(PostConverter.restToBusiness(response.body()!!.payload!!).reversed())
                }
            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {


            }


        })
    }

}



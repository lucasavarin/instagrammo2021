package com.costa.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.adapter.PostAdapter
import com.costa.adapter.FollowersListAdapter
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.FollowersResponse
import com.costa.servizi.PostsResponse
import com.costa.utils.prefs
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
        val instance:HomeFragment= HomeFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ApiClient.getClient.getPost().enqueue(object : Callback<PostsResponse> {

            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if (!response.body()!!.payload!!.isNullOrEmpty()) {


                    val layoutManagerPosts = LinearLayoutManager(context)
                    rv_post.layoutManager = layoutManagerPosts
                    rv_post.adapter = PostAdapter(response.body()!!.payload!!)
                }
            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {

                Toast.makeText(
                    this@HomeFragment.context,
                    "i server non sono al momento disponibili",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

        ApiClient.getClient.getFollowes(userId).enqueue(object : Callback<FollowersResponse> {

            override fun onResponse(call: Call<FollowersResponse>, response: Response<FollowersResponse>) {
                if (!response.body()!!.payload!!.isNullOrEmpty()) {


                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recycleHorizontal.layoutManager = layoutManager
                    recycleHorizontal.adapter = FollowersListAdapter(response.body()!!.payload!!)
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {

                Toast.makeText(
                    this@HomeFragment.context,
                    "i server non sono al momento disponibili",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

       /* fun getFollower() {
            ApiClient.getClient.getFollowes(prefs!!.username).enqueue(object : Callback<FollowersResponse> {
                override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                    println("accesso fallito")
                }

                override fun onResponse(
                    call: Call<FollowersResponse>,
                    response: Response<FollowersResponse>
                ) {
                    followers = response.body()?.payload?.toMutableList()!!

                    Log.i("info",response.body()?.result.toString())
                }
            })
        } */

    }

}



package com.example.instagrammo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.bean.Follower
import com.example.instagrammo.bean.Post
import com.example.instagrammo.R
import com.example.instagrammo.adapter.FollowerListRecyclerAdapter
import com.example.instagrammo.adapter.PostAdapter
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.FollowerResponse
import com.example.instagrammo.response.PostResponse
import com.example.instagrammo.view.prefs
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var followers: MutableList<Follower> = mutableListOf()
    private var posts: List<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFollower()
        getPosts()
    }


    fun getFollower() {
        ApiClient.getClient.getFollowers(prefs!!.idProfilo)
            .enqueue(object : Callback<FollowerResponse> {
                override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                    println("hai fallito-----------> $t.message ")
                }

                override fun onResponse(call: Call<FollowerResponse>, response: Response<FollowerResponse>) {
                    followers = response.body()?.payload?.toMutableList()!!
                    val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    homeFollowerRecyclerView.layoutManager = linearLayoutManager
                    homeFollowerRecyclerView.adapter = FollowerListRecyclerAdapter(followers)
                    Log.i("info", response.body()?.result.toString())
                }

            })
    }

    fun getPosts() {
        ApiClient.getClient.getPost().enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                println("hai fallito-----------> " + t.message)
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (!response.body()?.payload.isNullOrEmpty()) {
                    posts = response.body()?.payload?.toMutableList()!!
                    val linearLayoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    homePostListLayout.layoutManager = linearLayoutManager
                    homePostListLayout.adapter = PostAdapter(posts)
                }
                Log.i("info", response.body()?.result.toString())
            }

        })
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}
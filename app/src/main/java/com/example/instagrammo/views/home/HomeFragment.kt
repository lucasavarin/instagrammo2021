package com.example.instagrammo.views.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.followers.FollowerBean
import com.example.instagrammo.beans.followers.FollowerProfile
import com.example.instagrammo.beans.followers.FollowersResponse
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.beans.posts.PostBean
import com.example.instagrammo.beans.posts.PostResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.recyclerview.adapter.*
import com.example.instagrammo.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var mView: View

    private var listenerPost: OnPostItemClickListener? = null

    private var listenerFollow: OnFollowItemClickListener? = null

    private lateinit var itemsPost: List<PostBean>

    private lateinit var itemsFollow: List<FollowerBean>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_home, container, false)

        getData()

        return this.mView
    }

    private fun getData() {

        ApiClient.GetClient.getPosts()
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    if(response.isSuccessful &&  response.body()?.result == true) {
                        itemsPost = response.body()!!.payload!!.map { post ->
                            PostBean.convert(post)
                        }
                        setAdapterPost()
                    }
                    //itemsPost = response.body()?.payload!!.toMutableList()
                    //setAdapterPost()
                }

            })

        ApiClient.GetClient.getFollowers(prefs.rememberIdProfile!!)
            .enqueue(object : Callback<FollowersResponse> {
                override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<FollowersResponse>, response: Response<FollowersResponse>) {
                    if(response.isSuccessful &&  response.body()?.result == true) {
                        itemsFollow = response.body()!!.payload!!.map { follower ->
                            FollowerBean.convert(follower)

                        }
                        setFollowAdapter()
                    }
                    //itemsFollow = response.body()?.payload!!.toMutableList()
                    //setFollowAdapter()
                }

            })
    }

    private fun setAdapterPost() {
        val recyclerView = this.mView.home_post_recycler
        if (recyclerView is RecyclerView ) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost )
            }
        }
    }

    private fun setFollowAdapter() {
        val recyclerView = this.mView.home_follow_recycler
        if (recyclerView is RecyclerView) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
                adapter = ItemFollowRecyclerViewAdapter(this.context, itemsFollow, listenerFollow)
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPostItemClickListener) {
            listenerPost = context
        } else {
            throw RuntimeException("$context must implement OnPostItemClickListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listenerPost = null
    }

    companion object {
        var newInstance: HomeFragment = HomeFragment()
    }

}
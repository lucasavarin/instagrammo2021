package com.lynx.instagrammo.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.FollowerResponse
import com.lynx.instagrammo.networking.PostResponse
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.recyclerView.FollowerListAdapter
import com.lynx.instagrammo.view.recyclerView.FollowerListViewHolder
import com.lynx.instagrammo.view.recyclerView.PostListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), FollowerListViewHolder.OnFollowerClickListener {

    lateinit private var listener: HomeFragmentInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /*-------------- ON VIEW CREATED--------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*-------------- chiamata get follower--------------*/
        ApiClient.GetClient.getFollower(prefs.userId).enqueue(object : Callback<FollowerResponse> {
            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                if (response.isSuccessful)
                    followerLayoutManager(response.body()!!.payload)
            }

            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                Log.i("ERRORE", "Chiamata fallita")
            }
        })

        /*-------------- chiamata get Post--------------*/
        ApiClient.GetClient.getPost().enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (!(response.body()!!.payload.isNullOrEmpty()))
                    postLayoutManager(response.body()?.payload)
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.i("ERRORE", "Chiamata fallita")
            }

        })
    }

    /*-------------- LAYOUT MANAGER --------------*/
    //  POST
    private fun postLayoutManager(payload: List<Post>?) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        V_FollowerListLayout.layoutManager = linearLayoutManager
        if (!payload.isNullOrEmpty())
            V_FollowerListLayout.adapter = PostListAdapter(payload)
        else
            Log.i("ERRORE", payload?.size.toString())
    }

    //  FOLLOWER
    private fun followerLayoutManager(payload: List<Follower>?) {
        val linearLayoutManager2 = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        H_FollowerListLayout.layoutManager = linearLayoutManager2

        if (!payload.isNullOrEmpty())
            H_FollowerListLayout.adapter = FollowerListAdapter(payload, this)
        else
            Log.i("ERRORE", payload?.size.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HomeFragmentInterface)
            listener = context
    }

    companion object{
        val newInstance: HomeFragment = HomeFragment()
    }

    override fun onItemClick(item: Follower, position: Int) {
    listener.goToProfilepressed(item)
    }

    interface HomeFragmentInterface{
    fun goToProfilepressed(follower: Follower)
    }

}

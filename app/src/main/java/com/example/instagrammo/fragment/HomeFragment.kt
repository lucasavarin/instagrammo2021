package com.example.instagrammo.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.Follower
import com.example.instagrammo.MainActivity
import com.example.instagrammo.R
import com.example.instagrammo.adapter.FollowerListRecyclerAdapter
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.AuthResponse
import com.example.instagrammo.response.FollowerResponse
import com.example.instagrammo.view.prefs
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var followers: MutableList<Follower> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFollower()
        setAdapter()

    }


    fun getFollower() {
   ApiClient.getClient.getFollowers(prefs!!.idProfilo).enqueue(object : Callback<FollowerResponse>{
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                println("hai fallito-----------> " + t.message)
            }

            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                //spostare la riga 62 ,dichiarere una variabile dove inserrire
                followers = response.body()?.payload?.toMutableList()!!

                Log.i("info",response.body()?.result.toString())
            }

        })
    }

    private fun setAdapter() {

        val linearLayoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        homeFollowerRecyclerView.layoutManager = linearLayoutManager
        homeFollowerRecyclerView.adapter = FollowerListRecyclerAdapter(followers)


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}
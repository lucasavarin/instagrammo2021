package com.lynxspa.instagrammo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.recyclerView.FollowerListRecyclerAdapter
import com.lynxspa.instagrammo.retrofit.Follower
import com.lynxspa.instagrammo.retrofit.FollowerResponse
import com.lynxspa.instagrammo.singleton.ApiClient
import com.lynxspa.instagrammo.singleton.prefs
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    var follower: MutableList<Follower> = mutableListOf()


    companion object {

        fun makeIstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFollower()
    }

    fun getFollower() {
        ApiClient.getClient.getFollower(prefs.rememberIdProfile)
            .enqueue(object : Callback<FollowerResponse> {
                override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                    println("sono qui" + t.message)
                }

                override fun onResponse(
                    call: Call<FollowerResponse>,
                    response: Response<FollowerResponse>
                ) {
                    Log.i("info", response.body().toString())
                    if (response.body()?.payload != null){
                        follower = response.body()!!.payload!!.toMutableList()
                    }
                    val linearLayoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    home_follow_recycler.layoutManager = linearLayoutManager
                    home_follow_recycler.adapter = FollowerListRecyclerAdapter(follower)


                }

            })

    }

}
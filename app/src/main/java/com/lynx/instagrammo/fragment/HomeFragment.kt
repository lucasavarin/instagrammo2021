package com.lynx.instagrammo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynx.instagrammo.*
import com.lynx.instagrammo.API.ApiClient
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.recyclerView.PostListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    fun layoutManager(payload: List<Post>?){
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        V_FollowerListLayout.layoutManager = linearLayoutManager
        if (!payload.isNullOrEmpty()) {
            V_FollowerListLayout.adapter = PostListAdapter(payload)
        }else
        {
            Log.i("ERRORE", payload?.size.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiClient.GetClient.getPost().enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (!(response.body()!!.payload.isNullOrEmpty())) {
                    layoutManager(response.body()?.payload)
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.i("ERRORE", "Chiamata fallita")
            }
    })


        val linearLayoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        H_FollowerListLayout.layoutManager = linearLayoutManager2
        H_FollowerListLayout.adapter = FollowerListAdapter(loadFollowers())


    }

}

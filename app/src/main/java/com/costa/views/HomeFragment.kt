package com.costa.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.adapter.PostAdapter
import androidx.recyclerview.widget.RecyclerView
import com.costa.adapter.FollowersListAdapter
import com.costa.adapter.PostAdapter
import com.costa.beans.FollowerOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
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
                if (!response.body()!!.payload!!.isEmpty()) {


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


    }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycleHorizontal.layoutManager = layoutManager
        recycleHorizontal.adapter = FollowersListAdapter(lista)
}



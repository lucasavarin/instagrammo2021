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
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.beans.posts.PostResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.recyclerview.adapter.*
import com.example.instagrammo.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){

    private lateinit var mView: View

    private var listenerPost: OnPostItemClickListener? = null

    private var itemsPost: MutableList<Post> = mutableListOf()

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
        ApiClient.GetClient.getPosts(prefs.rememberToken!!).enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.i("INFORMATION", t.message.toString())
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                //itemsPost = response.body()?.payload!!.toMutableList()
                //Log.i("INFORMATION --> vediamo", items.toString())
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
        var homeFragment : HomeFragment = HomeFragment()
    }

}
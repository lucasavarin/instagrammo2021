package com.example.instagrammo.views.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.bean.Post
import com.example.instagrammo.recyclerview.adapter.*
import com.example.instagrammo.views.follow.FollowFragment

class HomeFragment : Fragment(){

    private lateinit var mView: View

    private var listener: OnPostItemClickListener? = null

    private var items: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.mView = inflater.inflate(R.layout.fragment_home, container, false)

        return this.mView
    }

    private fun setAdapter() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.home_post_recycler)
        if (recyclerView != null && recyclerView.isNotEmpty() ) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, items, listener )
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPostItemClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnPostItemClickListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        var homeFragment : HomeFragment = HomeFragment()
    }

}
package com.lynx.instagrammo.recycleView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Follower

class FollowerListAdapter(private val followers : List<Follower>) : RecyclerView.Adapter<FollowerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = followers.size

    override fun onBindViewHolder(holder: FollowerListViewHolder, position: Int) {

    }
}


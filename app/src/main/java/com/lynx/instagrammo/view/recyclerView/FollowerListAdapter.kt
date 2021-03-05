package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower

class FollowerListAdapter(private val followers: List<Follower>, private var callback :(item: Follower, position: Int)-> Unit) : RecyclerView.Adapter<FollowerListViewHolder>() {
    //onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_follower, parent, false)
        return FollowerListViewHolder(inflatedView)
    }

    //onBindViewHolder
    override fun onBindViewHolder(holder: FollowerListViewHolder, position: Int) {
        val item = followers[position]
        holder.bindFollow(item, callback)
    }

    //getItemCount
    override fun getItemCount() = followers.size
}


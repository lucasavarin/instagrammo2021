package com.lynxspa.instagrammo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.FollowerResponse
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.retrofit.Follower

class FollowerListRecyclerAdapter(private val followers : List <Follower> ) :
RecyclerView.Adapter<FollowerListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListHolder {
        val inflatedView=  LayoutInflater.from(parent.context).inflate(R.layout.item_follower, parent, false)
        return FollowerListHolder(inflatedView)
    }

    override fun getItemCount() = followers.size

    override fun onBindViewHolder(holder: FollowerListHolder, position: Int) {
        holder.bindFollower(followers.get(position))
    }

}
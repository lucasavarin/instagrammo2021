package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.Follower
import com.example.instagrammo.R

class FollowerListRecyclerAdapter (private val followers: List<Follower>): RecyclerView.Adapter<FollowerListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListHolder {
        val inflate =LayoutInflater.from(parent.context).inflate(R.layout.follower,parent,false)

        return FollowerListHolder(inflate)
    }

    override fun getItemCount() = followers.size

    override fun onBindViewHolder(holder: FollowerListHolder, position: Int) {
        holder.bindFollower(followers.get(position))
    }
}
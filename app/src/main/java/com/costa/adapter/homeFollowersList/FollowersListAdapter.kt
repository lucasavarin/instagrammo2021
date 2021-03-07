package com.costa.adapter.homeFollowersList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.business.Follower
import com.costa.beans.rest.FollowerOut
import com.costa.instagrammo.R

class FollowersListAdapter(private val followers: List<Follower>) :
    RecyclerView.Adapter<FollowersListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersListHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.follow_item, parent, false)
        return FollowersListHolder(view)
    }

    override fun getItemCount() = followers.size


    override fun onBindViewHolder(holder: FollowersListHolder, position: Int) {
        holder.bindFollower(followers[position])

    }

}

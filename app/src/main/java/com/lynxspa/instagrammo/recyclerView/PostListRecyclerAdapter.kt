package com.lynxspa.instagrammo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.retrofit.Follower
import com.lynxspa.instagrammo.retrofit.Post

class PostListRecyclerAdapter(private val followers : List <Follower>, private val posts: List<Post>) :
    RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val inflatedView=  LayoutInflater.from(parent.context).inflate(R.layout.item_follower, parent, false)
        return PostListHolder(inflatedView)
    }

    override fun getItemCount()= posts.size


    override fun onBindViewHolder(holder: PostListHolder, position: Int) {
        holder.bindPost(posts.get(position),followers.get(position))
    }

}
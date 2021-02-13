package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.bean.Post
import com.example.instagrammo.R


class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.postfollower, parent, false)

        return PostListHolder(inflate)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {
        holder.bindPost(posts[position])
    }


}
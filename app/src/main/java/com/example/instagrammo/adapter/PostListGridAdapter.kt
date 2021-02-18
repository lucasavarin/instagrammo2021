package com.example.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.bean.Post

class PostListGridAdapter (val postsProfile: List<Post>): RecyclerView.Adapter<PostGridHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostGridHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.post_grid, parent, false)

      return PostGridHolder(inflate)
    }

    override fun onBindViewHolder(holder: PostGridHolder, position: Int) {
        holder.assemblePosts(postsProfile[position])
    }

    override fun getItemCount(): Int {
        return postsProfile.size
    }
}
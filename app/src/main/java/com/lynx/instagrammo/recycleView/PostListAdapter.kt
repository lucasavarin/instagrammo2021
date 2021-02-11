package com.lynx.instagrammo.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.loadPosts

class PostListAdapter(private val posts : List<Post>) : RecyclerView.Adapter<PostListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post , parent, false)
        return PostListViewHolder(inflatedView)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
      //  holder.bindFollower(followers[position])
        val item = posts[position]
               holder.bindPost(item)
               //holder.textImage.setText(item.title)
    }
}
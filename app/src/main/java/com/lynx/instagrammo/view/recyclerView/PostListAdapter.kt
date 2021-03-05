package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Post

class PostListAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostListViewHolder>() {

    //onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        return PostListViewHolder(inflatedView)
    }

    //onBindViewHolder
    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val item = posts[position]
        holder.bindPost(item)
        //holder.textImage.setText(item.title)
    }

    //getItemCount
    override fun getItemCount() = posts.size
}
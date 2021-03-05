package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.MyPost

class ProfileGridAdapter(private var post: List<MyPost>) : RecyclerView.Adapter<ProfileGridViewHolder>() {

    //onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileGridViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mypost_img_grid, parent, false)
        return ProfileGridViewHolder(inflatedView)
    }

    //onBindViewHolder
    override fun onBindViewHolder(holder: ProfileGridViewHolder, position: Int) {
        val item = post[position]
        holder.bindGridPost(item)
    }

    //getItemCount
    override fun getItemCount(): Int = post.size

}
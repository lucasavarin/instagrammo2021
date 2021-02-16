package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.bean.Profile

class ProfileListAdapter(private var post : List<MyPost>): RecyclerView.Adapter<ProfileListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mypost_img_list, parent, false)
        return ProfileListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        val item = post[position]
        holder.bindListProfile(item)
    }

    override fun getItemCount(): Int = post.size

}
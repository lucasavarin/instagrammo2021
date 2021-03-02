package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post

class NotificationListAdapter(private val posts : List<Post> /*, private var callback :(item: Post, position: Int)-> Unit*/) : RecyclerView.Adapter<NotificationListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notification , parent, false)
        return NotificationListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NotificationListViewHolder, position: Int) {
        val item = posts[position]
        holder.bindNotification(item/*, callback*/)
    }

    override fun getItemCount(): Int = posts.size
}
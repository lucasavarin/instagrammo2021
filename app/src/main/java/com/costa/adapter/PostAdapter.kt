package com.costa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.FollowerOut
import com.costa.beans.PostOut
import com.costa.instagrammo.R

class PostAdapter(private val posts: List<PostOut>) :
    RecyclerView.Adapter<PostViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post_home, parent, false)

        return PostViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = posts[position]
        holder.bindPost(item)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = posts.size


}
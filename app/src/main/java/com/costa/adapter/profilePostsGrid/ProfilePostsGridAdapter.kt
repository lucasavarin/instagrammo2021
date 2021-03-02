package com.costa.adapter.profilePostsGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.rest.MyProfilePostsOut
import com.costa.instagrammo.R


class ProfilePostsGridAdapter(private val posts: List<MyProfilePostsOut>) :
    RecyclerView.Adapter<ProfilePostsGridViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostsGridViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_post_grid, parent, false)

        return ProfilePostsGridViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ProfilePostsGridViewHolder, position: Int) {
        val item = posts[position]
        holder.bindProfilePost(item)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = posts.size


}
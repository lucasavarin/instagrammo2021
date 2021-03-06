package com.costa.adapter.addpost

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.business.PicSumImage

import com.costa.beans.rest.PicSumImageOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostsGridAdAdapter (private val posts: List<PicSumImageOut>, private val callback:(imageOut: PicSumImage)->Unit) :
    RecyclerView.Adapter<AddPostsGridViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPostsGridViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_post_grid, parent, false)

        return AddPostsGridViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: AddPostsGridViewHolder, position: Int) {

       holder.bindImagePost(posts[position],callback)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = posts.size


}


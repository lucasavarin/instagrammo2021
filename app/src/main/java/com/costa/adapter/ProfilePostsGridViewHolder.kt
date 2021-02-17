package com.costa.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.MyPosts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile_post_grid.view.*

class ProfilePostsGridViewHolder (private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


    private lateinit var post: MyPosts

    fun bindProfilePost(myPost: MyPosts){
        post=myPost

        Picasso.get()
            .load(post.picture)
            .into(v.img_post_grid)

    }
    override fun onClick(v: View?) {
        Toast.makeText(v!!.context,post.toString(), Toast.LENGTH_LONG).show()
    }
}
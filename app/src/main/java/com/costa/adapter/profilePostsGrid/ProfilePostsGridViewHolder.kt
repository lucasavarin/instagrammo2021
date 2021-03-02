package com.costa.adapter.profilePostsGrid

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.rest.MyProfilePostsOut
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile_post_grid.view.*

class ProfilePostsGridViewHolder (private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


    private lateinit var post: MyProfilePostsOut

    fun bindProfilePost(myPost: MyProfilePostsOut){
        post=myPost

        Picasso.get()
            .load(post.picture)
            .into(v.img_post_grid)

    }
    override fun onClick(v: View?) {
        Toast.makeText(v!!.context,post.toString(), Toast.LENGTH_LONG).show()
    }
}
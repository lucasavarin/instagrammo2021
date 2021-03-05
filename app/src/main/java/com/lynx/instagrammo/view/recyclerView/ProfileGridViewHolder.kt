package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.MyPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mypost_img_grid.view.*

class ProfileGridViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
    lateinit private var post: MyPost

    //bindGridPost
    fun bindGridPost(post: MyPost) {
        this.post = post
        Picasso
                .get()
                .load(post.picture)
                .resize(500, 500)
                .into(v.grid_profile_img)

    }
}
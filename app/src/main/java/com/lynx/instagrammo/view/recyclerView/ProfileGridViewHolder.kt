package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.bean.Profile
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.item_mypost_img_grid.view.*

class ProfileGridViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
    lateinit private var post : MyPost

    fun bindGridPost(post: MyPost){
        this.post = post
        Picasso
                .get()
                .load(post.picture)
                .into(v.grid_profile_img)

        Picasso
                .get()
                .load(post.picture)
                .transform(BlurTransformation(v.context, 25, 3))
                .into(v.blur_grid_profile_img)

    }
}
package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.MyPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mypost_img_list.view.*

class ProfileListViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {

    lateinit private var post : MyPost

    fun bindListProfile(post: MyPost){
        this.post = post
        v.profile_list_title.text = post.title
        v.profile_list_date.text = post.uploadTime
        Picasso
            .get()
            .load(post.picture)
            .fit()
            .centerCrop()
            .into(v.list_profile_img)
    }

}
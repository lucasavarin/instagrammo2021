package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.bean.Post
import com.example.instagrammo.view.CircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.follower.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.post_grid.view.*
import kotlinx.android.synthetic.main.postfollower.view.*

class PostGridHolder (view: View) :  RecyclerView.ViewHolder(view){
    var v: View = view
    var post: Post? = null

    fun assemblePosts(post: Post){
        this.post = post
        Picasso.get().load(post.picture).fit().into(v.imagePost)

    }
}
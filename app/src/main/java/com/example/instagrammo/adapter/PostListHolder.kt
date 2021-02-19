package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.bean.Follower
import com.example.instagrammo.bean.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.postfollower.view.*

class PostListHolder(v : View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var posts: Post? = null

    fun bindPost(post : Post){
        this.posts = post
        Picasso.get().load(post.profile.picture)
        view.username.text = post.profile.name
        Picasso.get().load(post.picture).fit().into(view.image_post)
        view.description_post.text = post.title
    }
}
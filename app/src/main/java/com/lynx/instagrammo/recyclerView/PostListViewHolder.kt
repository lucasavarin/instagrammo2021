package com.lynx.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PostListViewHolder(private var v : View): RecyclerView.ViewHolder(v) {

    lateinit private var posts: Post

    fun bindPost(posts :Post){
        this.posts = posts
       // v.likes_text.text = posts.title
        v.username_text.text = posts.profile.name
        v.description_text.text = posts.title
        Picasso.get().load(posts.picture).into(v.post_image)
    }
}
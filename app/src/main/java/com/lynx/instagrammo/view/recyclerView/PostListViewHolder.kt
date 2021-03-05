package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PostListViewHolder(private var v : View): RecyclerView.ViewHolder(v) {

    lateinit private var posts: Post

    //bindPost
    fun bindPost(posts :Post){
        this.posts = posts
        v.username_text.text = posts.profile?.name
        v.description_text.text = posts.title
        Picasso
            .get()
            .load(posts.profile?.picture)
            .transform(CircleTransform())
            .into(v.user_photo_image)
        Picasso
            .get()
            .load(posts.picture)
            .fit()
            .into(v.post_image)

    }
}
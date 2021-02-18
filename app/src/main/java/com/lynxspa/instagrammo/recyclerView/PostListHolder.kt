package com.lynxspa.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.Follower
import com.lynxspa.instagrammo.retrofit.Post
import com.lynxspa.instagrammo.utility.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PostListHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var post: Post? = null
    private var followerP : Follower? = null
    fun bindPost(post: Post, followerP: Follower) {
        this.post= post
        this.followerP= followerP
        Picasso.get().load(followerP.picture).transform(CircleTransform()).into(view.imgProfileView)
        Picasso.get().load(post.picture).into(view.imgPostView)
        view.txtPost_view.text = followerP.name
    }
}
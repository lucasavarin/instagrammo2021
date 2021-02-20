package com.lynxspa.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.Follower
import com.lynxspa.instagrammo.retrofit.Post
import com.lynxspa.instagrammo.utility.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*
import okhttp3.internal.notifyAll

class PostListHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var post: Post? = null
    fun bindPost(post: Post) {
        this.post= post
        if (post.profile?.picture != null){
            Picasso.get().load(post.profile?.picture).transform(CircleTransform()).into(view.imgProfileView)
        }
        Picasso.get().load(post.picture).into(view.imgPostView)
        view.txtPost_view.text = post.profile?.name
    }
}
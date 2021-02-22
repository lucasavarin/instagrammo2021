package com.lynxspa.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.Post
import com.lynxspa.instagrammo.retrofit.Profile
import com.lynxspa.instagrammo.utility.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfiloListHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var post: Post? = null
    fun bindPostProfilo(post: Post) {
        this.post= post
        Picasso.get().load(post.picture).into(view.imgGridView)
    }
}
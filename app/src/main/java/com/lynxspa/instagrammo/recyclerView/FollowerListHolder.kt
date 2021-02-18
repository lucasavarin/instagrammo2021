package com.lynxspa.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.Follower
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follower.view.*
import com.lynxspa.instagrammo.utility.CircleTransform

class FollowerListHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var follower: Follower? = null
    fun bindFollower(follower: Follower) {
        this.follower = follower
        Picasso.get().load(follower.picture).transform(CircleTransform()).into(view.imgFollowerView)

    }

}
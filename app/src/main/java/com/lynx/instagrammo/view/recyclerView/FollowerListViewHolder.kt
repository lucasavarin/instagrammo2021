package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follower.view.*

class FollowerListViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {

    private var follower: Follower? = null

    fun bindFollow(follower: Follower) {
        this.follower = follower
      Picasso.get().load(follower.picture).transform(CircleTransform()).into(v.follow_image)
    }

}
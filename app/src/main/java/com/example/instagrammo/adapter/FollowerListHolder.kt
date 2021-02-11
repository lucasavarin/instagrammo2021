package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.Follower
import com.example.instagrammo.R
import com.example.instagrammo.view.CircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.follower.view.*

class FollowerListHolder(v : View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var follower: Follower? = null

    fun bindFollower(follower : Follower){
        this.follower = follower
        Picasso.get().load(follower.picture).transform(CircleTransformation()).into(view.followerImage)

    }


}
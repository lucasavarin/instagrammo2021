package com.lynxspa.instagrammo.recyclerView

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.FollowerResponse

class FollowerListHolder(v : View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view : View = v
    private var follower : FollowerResponse? = null
    fun bindFollower (follower: FollowerResponse){
        this.follower = follower
        // Picasso.get().load(follower.picture).into(view.followerImage)
    }

    override fun onClick(v: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }

}
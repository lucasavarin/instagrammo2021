package com.lynx.instagrammo.recycleView

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post
import kotlinx.android.synthetic.main.item_follower.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import java.io.File

class FollowerListViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {

    private var follower: Follower? = null
    lateinit var textImage: TextView

    fun bindFollow(follower: Follower) {
        this.follower = follower
        v.follow_image.setImageURI(Uri.EMPTY)
    }

}
package com.lynx.instagrammo.recycleView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.loadPosts
import kotlinx.android.synthetic.main.item_post.view.*

class PostListViewHolder(private var v : View): RecyclerView.ViewHolder(v) {

    private var posts: Post? = null
   lateinit var textImage: TextView

    fun bindPost(posts :Post){
        this.posts = posts
        v.likes_text.text = posts.profileId
    }
}
package com.costa.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.MyPosts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile_post_linear.view.*

class ProfilePostsLinearViewHolder(private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


    private lateinit var post: MyPosts

    fun bindProfilePost(myPost: MyPosts){
        this.post=myPost

        Picasso.get()
            .load(post.picture)
            .fit()
            .centerCrop()
            .into(v.img_my_post)
        v.tv_description.text=post.title
        v.tv_data.text=post.uploadTime

    }
    override fun onClick(v: View?) {
        Toast.makeText(v!!.context,post.toString(), Toast.LENGTH_LONG).show()
    }
}
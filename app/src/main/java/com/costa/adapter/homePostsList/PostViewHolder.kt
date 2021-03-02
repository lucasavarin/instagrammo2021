package com.costa.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.rest.PostOut
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_post_home.view.*

class PostViewHolder(private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


private lateinit var post: PostOut

    fun bindPost(postOut: PostOut){
        post=postOut
        Picasso.get()
            .load(post.profile.picture)
            .transform(CropCircleTransformation())
            .into(v.img_profilo)
        Picasso.get()
            .load(post.picture)
            .fit()
            .centerCrop()
            .into(v.img_post)

        v.tv_nome_Profilo.text=post.profile.name
        v.tv_description.text=post.title
        v.tv_data.text=post.uploadTime

    }
    override fun onClick(v: View?) {
        Toast.makeText(v!!.context,post.toString(),Toast.LENGTH_LONG).show()
    }
}
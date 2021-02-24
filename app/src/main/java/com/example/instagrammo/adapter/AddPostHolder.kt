package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.response.PictureResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture.view.*


class AddPostHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var picture: PictureResponse? = null

    fun bindPost(picture: PictureResponse) {
        this.picture = picture

        val updatedUrl = picture.download_url.split("/")
            .mapIndexed { index, value -> if (index == 5 || index == 6) "400" else value }
            .joinToString(separator = "/")
        Picasso.get().load(updatedUrl).fit().into(view.picture)

    }
}
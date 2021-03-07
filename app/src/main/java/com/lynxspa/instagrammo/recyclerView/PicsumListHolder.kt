package com.lynxspa.instagrammo.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.retrofit.PicsumResponse
import com.lynxspa.instagrammo.retrofit.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_follower.view.*
import java.io.File
class PicsumListHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var photo: PicsumResponse? = null
    fun bindPicsum(photo: PicsumResponse) {
        this.photo= photo
        val newUrl= photo.downloadUrl?.split("/")?.mapIndexed{ index, value ->
            if (index == 5 || index == 6)"400" else value
        }?.joinToString (separator = "/")
        Picasso.get().load(newUrl).into(view.addImage)
    }
}
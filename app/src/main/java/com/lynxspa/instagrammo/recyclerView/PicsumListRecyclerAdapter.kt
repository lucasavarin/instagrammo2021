package com.lynxspa.instagrammo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.retrofit.PicsumResponse
import com.lynxspa.instagrammo.retrofit.Post

class PicsumListRecyclerAdapter (private val photo: List<PicsumResponse>) :
    RecyclerView.Adapter<PicsumListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumListHolder {
        val inflatedView=  LayoutInflater.from(parent.context).inflate(R.layout.item_add, parent, false)
        return PicsumListHolder(inflatedView)
    }

    override fun getItemCount()= photo.size


    override fun onBindViewHolder(holder: PicsumListHolder, position: Int) {
        holder.bindPicsum(photo.get(position))
    }

}
package com.lynx.instagrammo.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.beanRest.PicsumImageRest

class AddGridAdapter(private var image: List<PicsumImageRest>, private var callback: (item: PicsumImageRest, position: Int) -> Unit) : RecyclerView.Adapter<AddGridViewHolder>() {
    //onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddGridViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mypost_img_grid, parent, false)
        return AddGridViewHolder(inflatedView)
    }

    //onBindViewHolder
    override fun onBindViewHolder(holder: AddGridViewHolder, position: Int) {
        val item = image[position]
        holder.bindGridImage(item, callback)
    }

    //getItemCount
    override fun getItemCount(): Int = image.size
}
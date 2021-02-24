package com.example.instagrammo.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.business.post.PostBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid3x.view.*

class ItemLoremRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<LoremBean>,
    private val mListener: OnImageItemClickListener
) : RecyclerView.Adapter<ItemLoremRecyclerViewAdapter.ViewHolder>(){

    override fun getItemCount(): Int = mValues.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemLoremRecyclerViewAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
            .inflate(R.layout.item_grid3x, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(
        holder: ItemLoremRecyclerViewAdapter.ViewHolder,
        position: Int) {

        val item = mValues[position]
        Picasso.get().load(item.download_url).resize(100,100).into(holder.itemImage)
        holder.itemView.setOnClickListener {
            mListener.onImageItemListener(item)
        }
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val itemImage = mView.item_image_grid

        override fun toString(): String {
            return super.toString() + "CIAO"
        }
    }

}



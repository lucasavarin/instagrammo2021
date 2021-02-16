package com.example.instagrammo.recyclerview.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.beans.posts.PostBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid3x.view.*

class ItemGridRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<PostBean>,
    private val mListener: OnPostItemClickListener?
    ) : RecyclerView.Adapter<ItemGridRecyclerViewAdapter.ViewHolder>(){

        val mOnClickListener: View.OnClickListener
        init {
            mOnClickListener = View.OnClickListener {
                mListener?.onPictureProfileItemListener("Cliccato")
            }
        }

        override fun getItemCount(): Int = mValues.size

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ItemGridRecyclerViewAdapter.ViewHolder {

            val layoutInflater = LayoutInflater.from(mContext)
                .inflate(R.layout.item_grid3x, parent, false)
            return ViewHolder(layoutInflater)
        }

        override fun onBindViewHolder(
            holder: ItemGridRecyclerViewAdapter.ViewHolder,
            position: Int) {

            val item = mValues[position]

            Picasso.get().load(item.picture).resize(100,100).into(holder.itemImage)

        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val itemImage = mView.item_image_grid

            override fun toString(): String {
                return super.toString() + "CIAO"
            }
        }

}

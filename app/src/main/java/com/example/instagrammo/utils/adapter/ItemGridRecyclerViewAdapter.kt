package com.example.instagrammo.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.business.post.PostProfileBean
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid3x.view.*
import kotlinx.android.synthetic.main.item_grid4x.view.*

class ItemGridRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<PostProfileBean>,
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
                .inflate(R.layout.item_grid4x, parent, false)
            return ViewHolder(layoutInflater)
        }

        override fun onBindViewHolder(
            holder: ItemGridRecyclerViewAdapter.ViewHolder,
            position: Int) {

            val item = mValues[position]

            Picasso.get().load(item.picture).resize(500,500).into(holder.itemImage)

        }
//////
        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val itemImage = mView.item_image_grid4x

            override fun toString(): String {
                return super.toString() + "CIAO"
            }
        }

}

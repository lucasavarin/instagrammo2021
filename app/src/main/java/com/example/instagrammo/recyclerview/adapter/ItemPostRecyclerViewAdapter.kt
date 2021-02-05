package com.example.instagrammo.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.bean.Post
import kotlinx.android.synthetic.main.item_post_home.view.*

class ItemPostRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<Post>,
    private val mListener: OnPostItemClickListener?
) : RecyclerView.Adapter<ItemPostRecyclerViewAdapter.ViewHolder>(){

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
    ): ItemPostRecyclerViewAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
            .inflate(R.layout.item_post_home, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(
        holder: ItemPostRecyclerViewAdapter.ViewHolder,
        position: Int) {

        val item = mValues[position]
        holder.profileImage.setImageURI(item.profile?.picture)
        //holder.profileName.text = item.profile?.name
        holder.postImage.setImageURI(item.picture)
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage = mView.profile_image
        val profileName = mView.profile_name
        val postImage = mView.profile_image
        val likeImage = mView.like_image

        override fun toString(): String {
            return super.toString() + "CIAO"
        }
    }
}
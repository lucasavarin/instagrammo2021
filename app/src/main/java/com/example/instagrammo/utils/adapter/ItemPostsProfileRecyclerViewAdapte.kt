package com.example.instagrammo.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.business.post.PostProfileBean
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_home.view.*

class ItemPostsProfileRecyclerViewAdapte (
    private val mContext: Context,
    private val mValues: List<PostProfileBean>,
    private val mListener: OnPostItemClickListener?,
    private val onlyPicture: Boolean = false
) : RecyclerView.Adapter<ItemPostsProfileRecyclerViewAdapte.ViewHolder>(){

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
    ): ItemPostsProfileRecyclerViewAdapte.ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
            .inflate(R.layout.item_post_home, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int) {

        val item = mValues[position]

        if (onlyPicture) {
            holder.profileImage.visibility = View.GONE
            holder.profileName.visibility = View.GONE
            holder.titlePost.visibility = View.GONE
            holder.datePost.visibility = View.GONE
            holder.lineSeparator.visibility = View.VISIBLE
            Picasso.get().load(item.picture).centerInside().resize(400,400).into(holder.postImage)
        }

    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage = mView.profile_image
        val profileName = mView.profile_name
        val postImage = mView.post_image
        val titlePost = mView.title_post
        val datePost = mView.date_post
        val lineSeparator = mView.line_separator

        override fun toString(): String {
            return super.toString() + "CIAO"
        }
    }

}
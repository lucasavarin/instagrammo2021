package com.example.instagrammo.utils.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.environment.networking.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_home.view.*


class ItemPostRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<PostBean>,
    private val mListener: OnPostItemClickListener?,
    private val onlyPicture: Boolean = false
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

        if (onlyPicture) {
            holder.profileImage.visibility = View.GONE
            holder.profileName.visibility = View.GONE
            holder.titlePost.visibility = View.GONE
            holder.datePost.visibility = View.GONE
            holder.lineSeparator.visibility = View.GONE
            Picasso.get().load(item.picture).into(holder.postImage)
        } else {

            if (item.profile?.picture!!.isNullOrBlank())
                Picasso.get().load(R.drawable.user).resize(500, 450).transform(CircleTransform()).into(holder.profileImage)
            else
                Picasso.get().load(item.profile?.picture!!).resize(500, 450).transform(CircleTransform()).into(holder.profileImage)
            holder.profileName.text = item.profile?.name
            Picasso.get().load(item.picture).resize(880,700).into(holder.postImage)
            holder.titlePost.text = item.title
            holder.datePost.text = item.uploadTime
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
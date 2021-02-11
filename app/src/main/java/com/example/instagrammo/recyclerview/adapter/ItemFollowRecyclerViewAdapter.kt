package com.example.instagrammo.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.followers.FollowerProfile
import com.example.instagrammo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follow_home.view.*

class ItemFollowRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<FollowerProfile>,
    private val mListener: OnFollowItemClickListener?) :
    RecyclerView.Adapter<ItemFollowRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mValues.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
            .inflate(R.layout.item_follow_home, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        Picasso.get().load(item.picture).transform(CircleTransform()).into(holder.imageProfile)
    }

    inner class ViewHolder(
        mView: View
    ) : RecyclerView.ViewHolder(mView){
        val imageProfile = mView.profile_image_follower
    }

}
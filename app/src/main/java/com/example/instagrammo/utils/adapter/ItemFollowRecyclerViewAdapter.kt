package com.example.instagrammo.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.followers.FollowerBean
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.listener.OnFollowItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follow_home.view.*

class ItemFollowRecyclerViewAdapter(
    private val mContext: Context,
    private val mValues: List<FollowerBean>,
    private val mListener: OnFollowItemClickListener?) :
    RecyclerView.Adapter<ItemFollowRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mValues.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
            .inflate(R.layout.item_follow_home, parent, false)
        impostaDimensioneImmagini(layoutInflater)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.nameFollowerText.text = item.name
        if (item.picture.isBlank())
            Picasso.get().load(R.drawable.user).resize(500, 450).transform(CircleTransform()).into(holder.imageProfile)
        else
            Picasso.get().load(item.picture).resize(200,200).transform(CircleTransform()).into(holder.imageProfile)


    }

    inner class ViewHolder(
        mView: View
    ) : RecyclerView.ViewHolder(mView){
        val imageProfile = mView.profile_image_follower
        val nameFollowerText = mView.name_follower
    }

    fun impostaDimensioneImmagini(
        mView: View
    ){
        /*var larghezza =  mView.profile_image_follower.getLayoutParams().width
        mView.profile_image_follower.getLayoutParams().height = larghezza/3
        mView.profile_image_follower.requestLayout()*/
    }

}
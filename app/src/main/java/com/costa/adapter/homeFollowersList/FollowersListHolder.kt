package com.costa.adapter.homeFollowersList

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.business.Follower
import com.costa.beans.rest.FollowerOut
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.follow_item.view.*

class FollowersListHolder(private var v: View) : RecyclerView.ViewHolder(v),
    View.OnClickListener {

    private var follower: Follower? = null

    fun bindFollower(follower: Follower) {
        this.follower = follower

       v.nomeUtente.text = follower.name
       Picasso.get().load(follower.picture).transform(CropCircleTransformation()).into(v.iconaUtente)
    }

    override fun onClick(v: View?) {
        Toast.makeText(itemView.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}


package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_follower.view.*

class FollowerListViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {

    private var follower: Follower? = null

    fun bindFollow(follower: Follower) {
        this.follower = follower
        v.follower_img_name.text = transformName(follower)

      Picasso
          .get()
          .load(follower.picture)
          .transform(CropCircleTransformation())
          .resize(200,200)
          .into(v.follow_image)
    }

    fun transformName(follower: Follower): String{
       val name :List<String>
        name = follower.name.split(" ")
        return name[0]
    }

}
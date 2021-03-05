package com.lynx.instagrammo.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationListViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {

    //bindNotification
    fun bindNotification(post: Post/*, callback :(item: Post, position: Int)-> Unit*/) {
        v.notify_name.text = post.profile!!.name
        v.notify_description.text = post.title
        v.notify_date.text = transformDate(post)
        Picasso
                .get()
                .load(post.picture)
                .transform(CircleTransform())
                .resize(30, 30)
                .into(v.notify_img)


        /* itemView.setOnClickListener{
             callback.invoke(post, adapterPosition)
         }*/
    }

    fun transformDate(posts: Post): String {
        val name: List<String>
        name = posts.uploadTime.split(" ")
        return name[1]
    }
}
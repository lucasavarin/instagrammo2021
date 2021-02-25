package com.lynx.instagrammo.view.recyclerView

import android.R
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mypost_img_grid.view.*


class AddGridViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
    lateinit var image: PicsumImageRest

    fun bindGridImage(image: PicsumImageRest, callback :(item: PicsumImageRest, position: Int)-> Unit) {
        this.image = image

        Picasso
                .get()
                .load(transformName(image))
                .into(v.grid_profile_img)

        itemView.setOnClickListener {
            callback.invoke(image, adapterPosition)
        }
    }

    fun transformName(image: PicsumImageRest): String {
        val picsumUrl: List<String>
        var result: String = ""
        picsumUrl = image.download_url.split("/")
        picsumUrl.forEach {
            if (picsumUrl[5].equals(it) || picsumUrl[6].equals(it)) {
                result = result + "/" + "500"
            } else if (!result.isNullOrBlank()) {
                result = result + "/" + it
            }else{
                result = it
            }
        }
        Log.i("RESULT :", result)
        return result
    }
/*

    val download_url_t : String = image.download_url.split("/")
        .mapIndexed{index, value-> if (index == 5 || index == 6)"500" else value}
        .joinToString(separator = "/")
*/
}
package com.costa.adapter

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.costa.beans.PicSumImage
import com.costa.beans.PicSumImageOut
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_add_post_grid.view.*


class AddPostsGridViewHolder (private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


    private lateinit var imageBean: PicSumImage

    fun bindImagePost(image: PicSumImageOut, callback:(imageOut: PicSumImage)->Unit){
        imageBean=image.toPicSumImage()
        imageBean.imageFormated=trasformPath(image)

        Picasso.get()
            .load(imageBean.imageFormated)
            .into(v.img_post_grid)
        itemView.setOnClickListener {
            callback.invoke(imageBean)
        }
    }

    override fun onClick(v: View?) {
        Toast.makeText(v!!.context,imageBean.toString(), Toast.LENGTH_LONG).show()
    }

    fun trasformPath(image: PicSumImageOut): String {
        val picsumUrl: List<String>
        var result: String = ""
        picsumUrl = image.download_url!!.split("/")
        picsumUrl.forEach {
            if (picsumUrl[5].equals(it) || picsumUrl[6].equals(it)) {
                result = result + "/" + "500"
            } else if(!result.isNullOrBlank()) {
                result = result + "/" + it
            }else{
                result=it
            }
        }
        Log.i("RESULT :", result)
        return result
    }
}
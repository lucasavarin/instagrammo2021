package com.example.instagrammo.recyclerview.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.retrofit.ApiClient
import com.squareup.picasso.Picasso
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
        //holder.profileImage.setImageBitmap(getImage(item.profile?.picture!!))
        holder.profileImage.setImageBitmap(getImage(item.profile?.picture!!))
        holder.profileName.text = item.profile.name
        holder.postImage.setImageBitmap(getImage(item.picture!!))
        holder.titlePost.text = item.title
        holder.datePost.text = item.uploadTime
    }


    private fun getImage(url: String): Bitmap {

        /** Nasconde il problema si dovrebbe implementare un service **/
        /** -si potrebbe usare un nuovo thread diverso dall applicazione ma
         *  bisogna essere consapevoli (AsyncTask)
         **/
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        /***************************************************************/

        //Picasso.get().load()

        val response = ApiClient.GetClient.getImage(url)
        val bodyResponse = response.execute().body()

        return BitmapFactory.decodeStream(bodyResponse?.byteStream())
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage = mView.profile_image
        val profileName = mView.profile_name
        val postImage = mView.post_image
        val titlePost = mView.title_post
        val datePost = mView.date_post

        override fun toString(): String {
            return super.toString() + "CIAO"
        }
    }
}
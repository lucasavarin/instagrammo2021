package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.response.PictureResponse

class AddPostAdapter(private val pictureList: List<PictureResponse>) :
    RecyclerView.Adapter<AddPostHolder>() {
   private val onImagePressed : ArrayList<(picture : PictureResponse) -> Unit> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPostHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.picture, parent, false)

        return AddPostHolder(inflate)
    }

    override fun getItemCount() = pictureList.size

    override fun onBindViewHolder(holder: AddPostHolder, position: Int) {
        holder.bindPost(pictureList[position])

        holder.itemView.setOnClickListener {
            pictureList[position]
            onImagePressed.forEach{
                it.invoke(pictureList[position])
            }
        }
    }
    fun setOnImageClickListner(callback: (picture : PictureResponse) -> Unit){
        onImagePressed.add(callback)
    }

}
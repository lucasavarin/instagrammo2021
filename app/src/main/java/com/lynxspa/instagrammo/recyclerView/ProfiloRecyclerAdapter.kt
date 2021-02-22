package com.lynxspa.instagrammo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.retrofit.Post
import com.lynxspa.instagrammo.retrofit.Profile

class ProfiloRecyclerAdapter (private val profilesPosts: List<Post>) :
    RecyclerView.Adapter<ProfiloListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfiloListHolder {
        val inflatedView=  LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfiloListHolder(inflatedView)
    }

    override fun getItemCount()= profilesPosts.size


    override fun onBindViewHolder(holder: ProfiloListHolder, position: Int) {
        holder.bindPostProfilo(profilesPosts.get(position))
    }

}
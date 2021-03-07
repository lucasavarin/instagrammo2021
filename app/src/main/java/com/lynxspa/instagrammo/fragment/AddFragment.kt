package com.lynxspa.instagrammo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.recyclerView.FollowerListRecyclerAdapter
import com.lynxspa.instagrammo.recyclerView.PicsumListRecyclerAdapter
import com.lynxspa.instagrammo.retrofit.Follower
import com.lynxspa.instagrammo.retrofit.FollowerResponse
import com.lynxspa.instagrammo.retrofit.PicsumResponse
import com.lynxspa.instagrammo.singleton.ApiClient
import com.lynxspa.instagrammo.singleton.PicsumPhotoClient
import com.lynxspa.instagrammo.singleton.prefs
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragment : Fragment() {
    var photo: MutableList<PicsumResponse> = mutableListOf()
    private lateinit var listener : ImageInterfaceFragment

    companion object {

        fun makeIstance(): AddFragment {
            return AddFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
    interface ImageInterfaceFragment {
        fun imageAddFragment ()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ImageInterfaceFragment){
            listener = context
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPhoto()
        //addImage.setOnClickListener {
          //listener.imageAddFragment()
        //}
    }
    fun getPhoto() {
        PicsumPhotoClient.getPicsumClient.getPhoto(2,30)
            .enqueue(object : Callback<Array<PicsumResponse>> {
                override fun onFailure(call: Call<Array<PicsumResponse>>, t: Throwable) {
                    println("sono qui" + t.message)
                }

                override fun onResponse(
                    call: Call<Array<PicsumResponse>>,
                    response: Response<Array<PicsumResponse>>
                ) {
                    Log.i("info", response.body().toString())
                    photo = response.body()!!.toMutableList()
                    val gridLayoutManager =
                        GridLayoutManager(context,3)
                    recyclerAdd.layoutManager = gridLayoutManager
                    recyclerAdd.adapter = PicsumListRecyclerAdapter(photo)
                }

            })

    }
}

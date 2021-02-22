package com.lynxspa.instagrammo.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.recyclerView.FollowerListRecyclerAdapter
import com.lynxspa.instagrammo.recyclerView.ProfiloRecyclerAdapter
import com.lynxspa.instagrammo.retrofit.*
import com.lynxspa.instagrammo.singleton.ApiClient
import com.lynxspa.instagrammo.singleton.prefs
import com.lynxspa.instagrammo.utility.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.toMutableList as toMutableList1

class ProfileFragment() : Fragment() {
    private lateinit var listener: ProfileInterfaceFragment
    private lateinit var profile: Profile
    var postProfiles: MutableList<Post> = mutableListOf()
    companion object {

        fun makeIstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfile()
        getPostProfile()
        btnEditProfile.setOnClickListener {
            listener.modificaProfiloFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    interface ProfileInterfaceFragment {
        fun modificaProfiloFragment ()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProfileInterfaceFragment){
            listener = context
        }
    }
    fun getProfile() {
        ApiClient.getClient.getProfilo(prefs.rememberIdProfile)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    println("sono qui" + t.message)
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    Log.i("info", response.body().toString())
                    if (response.body()?.payload != null){
                        //profilo = response.body()!!.payload!!.toMutableList1()
                        profileName.text = response.body()!!.payload!![0].name
                        profileDescription.text = response.body()!!.payload!![0].description
                        Picasso.get().load(response.body()!!.payload!![0].picture).transform(CircleTransform()).into(profileView)
                        numberFollower.text = response.body()!!.payload!![0].followersNumber
                        numberPost.text = response.body()!!.payload!![0].postsNumber
                    }
                }
            })

    }
    fun getPostProfile(){
        ApiClient.getClient.getProfiloPost(prefs.rememberIdProfile)
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    println("sono qui" + t.message)
                }

                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    Log.i("info", response.body().toString())
                    if (response.body()?.payload != null){
                        postProfiles = response.body()!!.payload!!.toMutableList1()
                    }
                    val gridLayoutManager =
                        GridLayoutManager(context,3)
                    profile_post_recycler.layoutManager = gridLayoutManager
                    profile_post_recycler.adapter = ProfiloRecyclerAdapter(postProfiles)
                }
            })

    }
}
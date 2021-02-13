package com.example.instagrammo.views.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.beans.posts.PostResponse
import com.example.instagrammo.beans.profile.Profile
import com.example.instagrammo.beans.profile.ProfileResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.recyclerview.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.recyclerview.adapter.OnPostItemClickListener
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var itemsProfile: Profile

    private var listenerPost: OnPostItemClickListener? = null

    private var itemsPost: MutableList<Post> = mutableListOf()

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        getData()

        return mView
    }

    private fun getData() {
        ApiClient.GetClient.getProfile(prefs.rememberIdProfile)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    itemsProfile = response.body()?.payload?.get(0)!!
                    populateDataView()
                }
            })

        ApiClient.GetClient.getPosts()
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    itemsPost = response.body()?.payload!!.toMutableList()
                    setAdapterPost()
                }

            })
    }

    private fun setAdapterPost() {
        val recyclerView = this.mView.recycler_post_mono_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost, true )
            }
        }
    }

    private fun populateDataView() {
        Picasso.get().load(R.drawable.bird).resize(1000,1000).transform(CircleTransform()).into(mView.profileImage)
        mView.postsNumber.text = itemsProfile.postsNumber
        mView.followersNumber.text = itemsProfile.followersNumber
        mView.name.text = itemsProfile.name
        mView.description.text = itemsProfile.description
/*
        if (itemsProfile.description.isNullOrBlank())
            viewContext.description.visibility = View.GONE

 */
    }



    companion object {
        var newInstance : ProfileFragment = ProfileFragment()
    }
}
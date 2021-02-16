package com.example.instagrammo.views.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.posts.Post
import com.example.instagrammo.beans.posts.PostResponse
import com.example.instagrammo.beans.profile.Profile
import com.example.instagrammo.beans.profile.ProfileResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.recyclerview.adapter.ItemGridRecyclerViewAdapter
import com.example.instagrammo.recyclerview.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.recyclerview.adapter.OnPostItemClickListener
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.views.BaseHomeActivity
import com.example.instagrammo.views.login.LogInActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.item_grid3x.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var itemsProfile: Profile

    private var listenerPost: OnPostItemClickListener? = null

    private var listenerButtonEdit: ButtonEditProfileListener? = null

    private var itemsPost: MutableList<Post> = mutableListOf()

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        getData()
        buttonsListener()

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
                    setAdapterGrid()
                }

            })
    }

    private fun buttonsListener(){
        mView.grid_cycle_image.setOnClickListener {
            setAdapterGrid()
        }

        mView.mono_cycle_image.setOnClickListener {
            setAdapterMono()
        }

        mView.edit_profile_button.setOnClickListener{
            listenerButtonEdit?.OnButtonPressedListener(true)
        }
    }

    private fun setAdapterGrid() {
        mView.recycler_post_grid_view.visibility = View.VISIBLE
        mView.recycler_post_mono_view.visibility = View.GONE
        val recyclerView = this.mView.recycler_post_mono_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost, true )
            }
        }
    }

    private fun setAdapterMono() {
        mView.recycler_post_mono_view.visibility = View.VISIBLE
        mView.recycler_post_grid_view.visibility = View.GONE
        val recyclerView = this.mView.recycler_post_grid_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 4)
                adapter = ItemGridRecyclerViewAdapter(this.context, itemsPost, listenerPost )
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonEditProfileListener) {
            listenerButtonEdit = context
        } else {
            throw RuntimeException("$context must implement ButtonEditProfileListener")
        }
    }

    companion object {
        var newInstance : ProfileFragment = ProfileFragment()
    }
}
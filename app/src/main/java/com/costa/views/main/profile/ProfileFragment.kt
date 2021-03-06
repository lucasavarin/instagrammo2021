package com.costa.views.main.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.adapter.profilePostsGrid.ProfilePostsGridAdapter
import com.costa.adapter.profilePostsList.ProfilePostsLinearAdapter
import com.costa.beans.rest.MyProfilePostsOut
import com.costa.beans.rest.ProfileOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.MyPostsResponce
import com.costa.servizi.ProfileResponse
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var listener: ProfileFragmentInterface
    private lateinit var profilo : ProfileOut
    private lateinit var callProfile:Call<ProfileResponse>
    private lateinit var callMyPosts:Call<MyPostsResponce>

    companion object{
        val instance: ProfileFragment =
            ProfileFragment()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ProfileFragmentInterface) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_modifica_profilo.setOnClickListener {
            listener.modifyProfilePressed(profilo)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        rv_posts_profilo_linear.visibility = View.GONE
                        rv_posts_profilo_gred.visibility = View.VISIBLE
                    }
                    1 -> {
                        rv_posts_profilo_linear.visibility = View.VISIBLE
                        rv_posts_profilo_gred.visibility = View.GONE
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        getProfile()
        getMyPosts()
    }

    override fun onDestroy() {
        callProfile.cancel()
        callMyPosts.cancel()
        super.onDestroy()
    }

    fun getMyPosts(){
        callMyPosts=ApiClient.getClient.getMyPosts(userId)
        callMyPosts.enqueue(object : Callback<MyPostsResponce> {

            override fun onResponse(
                call: Call<MyPostsResponce>,
                response: Response<MyPostsResponce>
            ) {
                if (!response.body()!!.payload!!.isNullOrEmpty()) {

                    linearRecycleView(response.body()!!.payload!!.reversed())
                    gredRecycleView(response.body()!!.payload!!.reversed())
                }
            }

            override fun onFailure(call: Call<MyPostsResponce>, t: Throwable) {

            }
        })
    }
    fun getProfile(){
        callProfile=ApiClient.getClient.getProfile(userId)
            callProfile.enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                profilo = response.body()!!.payload!![0]
                Picasso.get()
                    .load(response.body()!!.payload!![0].picture)
                    .transform(CropCircleTransformation())
                    .into(img_profilo)
                tv_nome_Profilo.text = response.body()!!.payload!![0].name
                tv_numero_followers.text = response.body()!!.payload!![0].followersNumber.toString()
                tv_numero_posts.text = response.body()!!.payload!![0].postsNumber.toString()
                tv_descrizione_Profilo.text = response.body()!!.payload!![0].description

            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            }


        })
    }

    fun linearRecycleView(payload: List<MyProfilePostsOut>) {
        rv_posts_profilo_linear.visibility = View.GONE
        rv_posts_profilo_linear.apply {
            val layoutManager = LinearLayoutManager(context)
            rv_posts_profilo_linear.layoutManager = layoutManager
            rv_posts_profilo_linear.adapter =
                ProfilePostsLinearAdapter(
                    payload
                )
        }
    }

    fun gredRecycleView(payload: List<MyProfilePostsOut>) {
        rv_posts_profilo_gred.apply {
            val layoutManager = GridLayoutManager(context, 3)
            rv_posts_profilo_gred.layoutManager = layoutManager
            rv_posts_profilo_gred.adapter =
                ProfilePostsGridAdapter(
                    payload
                )
        }
    }


    interface ProfileFragmentInterface {
        fun modifyProfilePressed(profile: ProfileOut)
    }
}
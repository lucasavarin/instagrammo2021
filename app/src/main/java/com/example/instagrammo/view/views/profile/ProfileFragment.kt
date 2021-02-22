package com.example.instagrammo.view.views.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.utils.adapter.ItemGridRecyclerViewAdapter
import com.example.instagrammo.utils.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.utils.adapter.OnPostItemClickListener
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var itemsProfile: ProfileBean

    private var listenerPost: OnPostItemClickListener? = null

    private var listenerButtonEdit: ButtonEditProfileListener? = null

    private lateinit var itemsPost: List<PostBean>

    private lateinit var mView: View

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)

        getData()
        setObservable()
        buttonsListener()

        return mView
    }

    private fun getData() {
        viewModel.setStateEvent(MainStateEvent.GetProfileEvent)
        viewModel.setStateEvent(MainStateEvent.GetPostsEvent)
    }

    private fun setObservable() {

            viewModel.dataStateProfile.observe(viewLifecycleOwner, Observer { dataStateProfile ->
                when (dataStateProfile) {
                    is DataState.Error -> {
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Success -> {
                        itemsProfile = dataStateProfile.data
                        populateDataView()
                    }
                }
            })

            viewModel.dataStatePost.observe(viewLifecycleOwner, Observer { dataStatePosts ->
                when (dataStatePosts) {
                    is DataState.Error -> {
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Success -> {
                        itemsPost = dataStatePosts.data
                        setAdapterGrid()
                    }
                }
            })

        /*
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
                    if(response.isSuccessful &&  response.body()?.result == true) {
                        itemsPost = response.body()!!.payload!!.map { post ->
                            PostBean.convert(post)
                        }
                        setAdapterGrid()
                    }
                    //itemsPost = response.body()?.payload!!.toMutableList()
                    //setAdapterPost()
                }

            })

         */
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
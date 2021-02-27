package com.example.instagrammo.view.views.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.adapter.PagerAdapter
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var itemsProfile: ProfileBean

    private var listenerButtonEdit: ButtonEditProfileListener? = null

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createTabLayout()
        getData()
        setObservable()
        buttonsListener()
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

        view?.edit_profile_button!!.setOnClickListener{
            listenerButtonEdit?.OnButtonPressedListener(true)
        }
    }

    private fun populateDataView() {
        Picasso.get().load(R.drawable.bird).resize(1000,1000).transform(CircleTransform()).into(requireView().profileImage)
        requireView().postsNumber.text = itemsProfile.postsNumber
        requireView().followersNumber.text = itemsProfile.followersNumber
        requireView().name.text = itemsProfile.name
        requireView().description.text = itemsProfile.description
    }


    private fun createTabLayout() {
        val tabLayout = requireView().findViewById(R.id.tabs) as TabLayout
        val viewPager = requireView().findViewById(R.id.viewpager) as ViewPager

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.grid_recycle))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mono_recycle))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val mLayoutManager = LinearLayoutManager(activity)

        mLayoutManager.orientation = LinearLayoutManager.VERTICAL

        viewPager.adapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener( TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        tabLayout.addOnTabSelectedListener((object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
                Log.i("INFORMATION", "onTabReselected")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.i("INFORMATION", "onTabUnselected")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.i("INFORMATION", "onTabSelected")
            }

        }))
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
package com.example.instagrammo.views

import android.os.Bundle
import com.example.instagrammo.R
import com.example.instagrammo.views.home.HomeFragment

class BaseHomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basehome)

        addFragment(HomeFragment.homeFragment, R.id.fragment_container)

    }
}
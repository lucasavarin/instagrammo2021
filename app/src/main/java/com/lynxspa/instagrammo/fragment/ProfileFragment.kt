package com.lynxspa.instagrammo.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.retrofit.Profile
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var listener: ProfileInterfaceFragment
    private lateinit var profile: Profile
    companion object {

        fun makeIstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
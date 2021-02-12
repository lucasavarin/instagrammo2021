package com.example.instagrammo.views.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    //Crasha
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        descrizioneProfilo.setText("ciao")
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ApiClient.GetClient.doGetProfile( ,"posts.php/{profileId}")
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.descrizioneProfilo.text =
        return view
    }

    companion object {
        var profileFragment : ProfileFragment = ProfileFragment()
    }
}
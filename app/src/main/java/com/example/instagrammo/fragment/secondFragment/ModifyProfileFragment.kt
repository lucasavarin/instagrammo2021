package com.example.instagrammo.fragment.secondFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagrammo.R
import com.example.instagrammo.fragment.ProfileFragment
import kotlinx.android.synthetic.main.fragment_modify_profile.*


class ModifyProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ModifyProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
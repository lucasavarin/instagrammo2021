package com.example.instagrammo.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.instagrammo.R
import com.example.instagrammo.fragment.secondFragment.ModifyProfileFragment
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    val modifyProfile = ModifyProfileFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this com.example.instagrammo.fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         edit_btn.setOnClickListener {
             val fragmentManager: FragmentManager? = fragmentManager
             var transaction : FragmentTransaction = fragmentManager?.beginTransaction()!!
             var fragment_new = ModifyProfileFragment()
             transaction.replace(R.id.fragment_container,fragment_new)
             transaction.addToBackStack(null)
             transaction.commit()
         }
    }



    companion object {
        @JvmStatic
         fun newInstance() =
             ProfileFragment().apply {
                 arguments = Bundle().apply {}
             }
    }

}

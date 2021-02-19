package com.lynxspa.instagrammo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lynxspa.instagrammo.R

class ModificaProfiloFragment: Fragment() {
    companion object {

        fun makeIstance(): ModificaProfiloFragment {
            return ModificaProfiloFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
}

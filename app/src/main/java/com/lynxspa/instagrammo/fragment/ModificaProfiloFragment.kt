package com.lynxspa.instagrammo.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.customView.NavigationHeader
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.navigation_header_view.*

class ModificaProfiloFragment: Fragment() {
    private lateinit var listener : BackToProfile
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
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackToProfile){
            listener = context
        }
    }
    interface BackToProfile {
        fun closeModificaProfiloFragment ()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationHeader.setOnBackListener {
            listener.closeModificaProfiloFragment()
        }
    }
}

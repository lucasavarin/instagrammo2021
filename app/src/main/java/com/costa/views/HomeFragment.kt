package com.costa.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costa.adapter.FollowersListAdapter
import com.costa.adapter.PostAdapter
import com.costa.beans.FollowerOut
import com.costa.instagrammo.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //Lista di prova
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lista = listOf(
            FollowerOut(1, "Donnarumma", "Portiere", ""),
            FollowerOut(2, "Kjaer", "Difensore", ""),
            FollowerOut(3, "Hernandez", "Terzino", ""),
            FollowerOut(4, "Kessie", "Centrocampista", ""),
            FollowerOut(5, "Bennacer", "Centrocampista", ""),
            FollowerOut(6, "Calhanoglu", "Trequartista", ""),
            FollowerOut(7, "Leao", "Ala", ""),
            FollowerOut(8, "Rebic", "Ala", ""),
            FollowerOut(9, "Ibraimovic", "Attacante", "")
        )

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycleHorizontal.layoutManager = layoutManager
        recycleHorizontal.adapter = FollowersListAdapter(lista)

    }
}



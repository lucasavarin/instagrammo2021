package com.example.instagrammo.view.views.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.utils.adapter.ItemLoremRecyclerViewAdapter
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment(){

    private val viewModel = MainViewModel()
    private lateinit var listaLorem : List<LoremBean>
    private lateinit var listenerImage: OnImageItemClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setStateEvent(MainStateEvent.GetAllLoremImagesEvent)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.dataStateAllLoremImages.observe(this.viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Error ->  {}
                is DataState.Loading -> { }
                is DataState.Success -> {
                    listaLorem = dataState.data.map {
                        LoremBean.convert(it)
                    }
                    setAdapterGrid(view)
                }
            }
        })
    }

    private fun setAdapterGrid(mView : View) {
        val recyclerView = view?.recycler_add_grid_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = GridLayoutManager(context, 3)
                adapter = ItemLoremRecyclerViewAdapter(this.context, listaLorem, listenerImage)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnImageItemClickListener) {
            listenerImage = context
        } else {
            throw RuntimeException("$context must implement OnImageItemClickListener")
        }
    }

    companion object {
        const val tag = "addFragment"

        var newInstance : AddFragment = AddFragment()
    }
}

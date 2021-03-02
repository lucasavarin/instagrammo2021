package com.example.instagrammo.view.views.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.profile.*
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest
import com.example.instagrammo.prefs
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.utils.ElementViewConverter.toEditable
import com.example.instagrammo.utils.listener.HeaderBackListener
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifica_profilo.*
import kotlinx.android.synthetic.main.fragment_modifica_profilo.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.profileImage

class EditProfileFragment : Fragment() {

    private lateinit var itemProfile: ProfileBean

    private lateinit var editProfileRequest: EditProfileRequest

    private lateinit var listener: HeaderBackListener

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modifica_profilo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        buttonsListener()
        setObservable()
    }

    private fun getData() {
        viewModel.setStateEvent(MainStateEvent.GetProfileEvent)
    }

    private fun setObservable() {

        viewModel.dataStateProfile.observe(viewLifecycleOwner, Observer { dataStateProfile ->
            when (dataStateProfile) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    itemProfile = dataStateProfile.data
                    populateDataView()
                }
            }
        })

        viewModel.dataStateEditProfile.observe(viewLifecycleOwner, Observer { dataStateEditProfile ->
            when (dataStateEditProfile) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> { }
            }
        })

      /*
        ApiClient.GetClient.putProfile(editProfileRequest)
            .enqueue(object : Callback<EditProfileResponse> {
                override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                    if (response.body()?.result!!) {
                       listener.removeFragmentListener()
                    }
                }
            })
        */
    }

    fun verificaDatiInseriti() {

        var nomeProfilo: String =
            if (NomeProfilo.text.isNullOrEmpty()) itemProfile.name!! else NomeProfilo.text.toString()
        var descrizioneProfilo: String =
            if (Descrizione.text.isNullOrEmpty()) itemProfile.description!! else Descrizione.text.toString()

        editProfileRequest =
            EditProfileRequest(
                prefs.idProfile,
                nomeProfilo,
                descrizioneProfilo,
                itemProfile.picture
            )
        viewModel.setStateEvent(MainStateEvent.PutEditProfileEvent(editProfileRequest))

    }
/*
    private fun getData() {
        ApiClient.GetClient.getProfile(prefs.rememberIdProfile)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    itemsProfile = response.body()?.payload?.map {
                        ProfileBean.convert(it)
                    }!![0]
                    populateDataView()
                }
            })
    }
*/
    private fun buttonsListener() {
        //bottone salva
        view?.close_edit_profile_button?.setOnClickListener {
            verificaDatiInseriti()
        }
        //bottone back nell header
        view?.topBarBackComponent?.setOnPressedListener{
            listener.removeFragmentListener(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HeaderBackListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement EditProfileFragmentListener")
        }
    }

    private fun populateDataView() {
        Picasso.get().load(R.drawable.bird).resize(1000, 1000).transform(CircleTransform())
            .into(view?.profileImage)
        view?.IdImmagine?.text = itemProfile.profileId?.toEditable()
        view?.NomeProfilo?.text = itemProfile.name?.toEditable()
        view?.Descrizione?.text = itemProfile.description?.toEditable()
        /* mView.postsNumber.text = itemsProfile.postsNumber
         mView.followersNumber.text = itemsProfile.followersNumber
         mView.name.text = itemsProfile.name
         mView.description.text = itemsProfile.description*/
    }

    companion object {
        var newInstance: EditProfileFragment = EditProfileFragment()
    }


}
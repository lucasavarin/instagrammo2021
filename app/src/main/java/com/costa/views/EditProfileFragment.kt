package com.costa.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.costa.beans.ProfileOut
import com.costa.instagrammo.MainActivity
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.BooleanRespomce
import com.costa.servizi.ProfileEditRequest
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment(){
    companion object{
        val instance:EditProfileFragment= EditProfileFragment()

    }
    lateinit var listener:Context
    var nome : String= ""
    var descrizione : String= ""
    var immagine : String= ""
    var imageId : String= ""
    var pathImg : String = "https://picsum.photos/id/$imageId/300/300"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nome=getArguments()!!.getString("nome").toString()
        descrizione=getArguments()!!.getString("description").toString()
        immagine=getArguments()!!.getString("picture").toString()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //linearLayoutManager = LinearLayoutManager(context)
        //gridLayoutManager = GridLayoutManager(context, 3)

        if (context is EditProfileFragmentInterface) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        header.setOnBackPressedListener { (listener as EditProfileFragmentInterface).back() }

        super.onViewCreated(view, savedInstanceState)

         setFilds()

        btn_conferma.setOnClickListener {
            imageId = id_immagine.text.toString()
            pathImg="https://picsum.photos/id/$imageId/300/300"
            Picasso.get()
                .load(pathImg)
                .transform(CropCircleTransformation())
                .into(immagineProfilo)


        }

        btn_salva_modifica.setOnClickListener {

            if (ceckFilds()) {
                imageId = id_immagine.text.toString()
                pathImg="https://picsum.photos/id/$imageId/300/300"

                ApiClient.getClient.putEditProfile(
                    userId, ProfileEditRequest(
                        userId,
                        editNomeProfilo.text.toString(),
                        editDescrizione.text.toString(),
                        pathImg
                    )
                ).enqueue(object : Callback<BooleanRespomce> {
                    override fun onResponse(
                        call: Call<BooleanRespomce>,
                        response: Response<BooleanRespomce>
                    ) {

                    }

                    override fun onFailure(call: Call<BooleanRespomce>, t: Throwable) {

                    }

                })

            }else {
                (Toast.makeText(
                    context,
                    "i server non sono al momento disponibili",
                    Toast.LENGTH_SHORT
                ).show())
            }
            (listener as EditProfileFragmentInterface).back()
        }
    }
    fun ceckFilds():Boolean{
        return (!editNomeProfilo.text.toString().isNullOrBlank()
                && !editDescrizione.text.toString().isNullOrBlank()
                && !id_immagine.text.toString().isNullOrBlank())
    }

    fun setFilds(){
        Picasso.get()
            .load(immagine)
            .transform(CropCircleTransformation())
            .into(immagineProfilo)
        editNomeProfilo.setText(nome)
        editDescrizione.setText(descrizione)
    }
    interface EditProfileFragmentInterface {
        fun back()
    }

    }



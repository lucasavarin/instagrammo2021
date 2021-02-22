package fragments

import android.content.Context
import utils.api.ApiClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bean.business.ProfileBean
import bean.rest.ProfileRequest
import com.example.instagrammo.R
import interfaces.ButtonBackToProfile
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.edit_top_bar.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.extensions.prefs

class EditProfileFragment : Fragment(){

    private lateinit var itemProfile: ProfileBean

    private var listenerReturnToProfile: ButtonBackToProfile? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idImmagine = "https://picsum.photos/id/${et_id_immagine.text.toString()}/200/300"
        var profileRequest = ProfileRequest(prefs.profileId, name = et_nome_profilo.text.toString(), description = et_descrizione_profilo.text.toString(), picture = idImmagine)
        btn_salva.setOnClickListener{
            ApiClient.getClient.putProfile(prefs.profileId, profileRequest).enqueue(object:Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    //TODO: finire di implementare il salvataggio dei dati nel modifica profilo
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    println("Chiamata non eseguita!!!! $t")
                }

            })
        }
        view.custom_edit_bar.setOnBackEditProfileListener {
            listenerReturnToProfile?.onClickListenerBackToProfile()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonBackToProfile) {
            listenerReturnToProfile = context
        } else {
            throw RuntimeException("$context must implement ButtonBackToProfile")
        }
    }

    /*private fun buttonsListener(){
        top_bar_edit_profile.setOnBackEditProfileListener{
            //remove fragment
        }
    }*/

    companion object {
        var newInstance: EditProfileFragment = EditProfileFragment()
    }

}
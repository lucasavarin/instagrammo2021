package fragments

import API.ApiClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bean.ProfileRequest
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.fragment_modifica_profilo.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.FollowerResponse
import utils.prefs

class EditProfileFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modifica_profilo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idImmagine = "https://picsum.photos/id/${et_id_immagine.text.toString()}/200/300"
        var profileRequest = ProfileRequest(prefs.profileId, name = et_nome_profilo.text.toString(), description = et_descrizione_profilo.text.toString(), picture = idImmagine)
        editButton.setOnClickListener{
            ApiClient.getClient.putProfile(prefs.profileId, profileRequest).enqueue(object:Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    //TODO: gestire la chiusura del modifica profilo
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    println("Chiamata non eseguita!!!! $t")
                }

            })
        }
    }

    companion object {
        var editprofileFragment : EditProfileFragment = EditProfileFragment()
    }

}
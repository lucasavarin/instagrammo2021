package fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*

class ProfileFragment : Fragment() {

    private var listenerButtonEdit: ButtonEditProfileListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editButton.setOnClickListener {
            listenerButtonEdit?.onClickListenerEditButtonProfile(true)
        }
    }

    companion object {
        var newInstance: ProfileFragment = ProfileFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonEditProfileListener) {
            listenerButtonEdit = context
        }
    }

}
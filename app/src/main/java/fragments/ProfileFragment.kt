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

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_profile, container, false)

        buttonsListener()

        return mView
    }

    companion object {
        var newInstance: ProfileFragment = ProfileFragment()
    }

    private fun buttonsListener(){
        /*mView.grid_cycle_image.setOnClickListener {
            setAdapterGrid()
        }
        mView.mono_cycle_image.setOnClickListener {
            setAdapterMono()
        }*/

        mView.editButton.setOnClickListener{
            listenerButtonEdit?.OnClickListenerEditButtonProfile(true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonEditProfileListener) {
            listenerButtonEdit = context
        } else {
            throw RuntimeException("$context devi implementare il ButtonEditProfileListener")
        }
    }

}
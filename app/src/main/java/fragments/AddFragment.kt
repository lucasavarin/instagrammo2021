package fragments

import adapters.AddGridAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bean.rest.PicsumImageRest
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.api.ApiClient

class AddFragment: Fragment() {

    var page = 0
    var limit = 30
    var listOfPost = mutableListOf<PicsumImageRest>()
    lateinit var callGetPicsum: Call<Array<PicsumImageRest>>
    lateinit var listener: AddFragmentInterface

    companion object {
        val newInstance: AddFragment = AddFragment()
    }

    //override della funzione onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    //override della funzione onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AddGridAdapter(listOfPost) { image, position ->       //adapter prende listOfPost, una variabile lateint che estende
            listener.addImage(image)                                            // l'interfaccia AddFragmentInterface. AddGridAdapter serve per gestire la recycleview
            // e infine il metodo addImage usa picsum per aggiungere immagini
        }
        //qui si ottiene l'effettiva riciclazione delle immagini
        addRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    callGetListImage(adapter)
                }
            }
        })
        callGetListImage(adapter)
    }

    //override della funzione onAttach
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddFragmentInterface)
            listener = context
    }

    //override della funzione onDestroy
    override fun onDestroy() {
        super.onDestroy()
        callGetPicsum.cancel()
    }

    //in questa funzione che estente l'adapter della recycleview viene usato picsum per fare la chiamata e ottenere le immagini
    fun callGetListImage(adapter: AddGridAdapter) {
        page++
        callGetPicsum = ApiClient.GetPicsumClient.getImage(page, limit)

        callGetPicsum.enqueue(object : Callback<Array<PicsumImageRest>> {
            override fun onResponse(call: Call<Array<PicsumImageRest>>, response: Response<Array<PicsumImageRest>>) {
                listOfPost.addAll(response!!.body()!!.toMutableList())
                adapter.notifyDataSetChanged()
                gridLayoutManager(adapter)
                if (listOfPost.size > 30) {
                    addRecyclerView.scrollToPosition(listOfPost.size - 42)
                }

            }
            override fun onFailure(call: Call<Array<PicsumImageRest>>, t: Throwable) {
                Log.i("ERRORE", "la chiamata getImage va in errore")
            }
        })
    }

    //questa funzione, chiamata sopra, usa il layoutmanager per collegare adapter
    //e viewholder della recycleview, e visualizzarle correttamente
    private fun gridLayoutManager(adapter: AddGridAdapter) {
        addRecyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, 3)
            addRecyclerView.layoutManager = gridLayoutManager
            addRecyclerView.adapter = adapter
        }
    }

    interface AddFragmentInterface {
        fun addImage(item: PicsumImageRest)
    }
}
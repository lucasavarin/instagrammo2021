package holders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import bean.rest.PicsumImageRest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mypost_img_grid.view.*


class AddGridViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
    lateinit var image: PicsumImageRest

    //bindGridImage
    fun bindGridImage(image: PicsumImageRest, callback: (item: PicsumImageRest, position: Int) -> Unit) {
        this.image = image

        Picasso
                .get()
                .load(transformName(image))
                .into(v.grid_profile_img)

        itemView.setOnClickListener {
            callback.invoke(image, adapterPosition)
        }
    }

    fun transformName(image: PicsumImageRest): String {
        val picsumUrl: List<String>
        var result = ""
        picsumUrl = image.download_url.split("/")
        picsumUrl.forEach {
            if (picsumUrl[5].equals(it) || picsumUrl[6].equals(it)) {
                result = result + "/" + "500"
            } else if (!result.isNullOrBlank()) {
                result = result + "/" + it
            } else {
                result = it
            }
        }
        Log.i("RESULT :", result)
        return result
    }
}
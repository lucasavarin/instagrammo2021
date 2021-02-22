package adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bean.rest.Post
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.item_post_home.view.*
import utils.api.ApiClient

class ItemPostRecyclerViewAdapter (
    private val mContext: Context,
    private val mValues: List<Post>,
    private val listener: OnPostItemClickListener?
) : RecyclerView.Adapter<ItemPostRecyclerViewAdapter.ViewHolder>() {

    val onClickListener: View.OnClickListener
    init {
        onClickListener = View.OnClickListener {
            listener?.onPictureProfileItemListener("Cliccato")
        }
    }

    override fun getItemCount(): Int = mValues.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ItemPostRecyclerViewAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
                .inflate(R.layout.item_post_home, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(
            holder: ItemPostRecyclerViewAdapter.ViewHolder,
            position: Int) {

        val item = mValues[position]
        holder.profileImage.setImageBitmap(getImage(item.profile?.picture!!))
        holder.profileName.text = item.profile.name
        holder.postImage.setImageBitmap(getImage(item.picture!!))
        holder.titlePost.text = item.title
        holder.datePost.text = item.uploadTime
    }

    private fun getImage(url: String): Bitmap {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val response = ApiClient.getClient.getImage(url)
        val bodyResponse = response.execute().body()

        return BitmapFactory.decodeStream(bodyResponse?.byteStream())
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage = mView.profile_image
        val profileName = mView.profile_name
        val postImage = mView.post_image
        val titlePost = mView.title_post
        val datePost = mView.date_post

        override fun toString(): String {
            return super.toString() + "CIAO"
        }
    }
}
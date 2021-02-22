package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bean.rest.FollowerProfile
import com.example.instagrammo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follow_home.view.*
import utils.extensions.CircleTransformation

class ItemFollowRecyclerViewAdapter (
        private val context: Context,
        private val values: List<FollowerProfile>,
        private val listener: OnFollowItemClickListener?) :
        RecyclerView.Adapter<ItemFollowRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
                .inflate(R.layout.item_follow_home, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get().load(item.picture).transform(CircleTransformation()).into(holder.imageProfile)
    }

    inner class ViewHolder(
            mView: View
    ) : RecyclerView.ViewHolder(mView){
        val imageProfile = mView.profile_image_follower
    }

}
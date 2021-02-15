package adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bean.Follower
import com.example.instagrammo.R

class FollowerAdapter(private val followers: List<Follower>) : RecyclerView.Adapter<FollowerListHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.story_item,parent,false)
        return FollowerListHolder(inflate)
    }

    override fun getItemCount() = followers.size

    override fun onBindViewHolder(holder: FollowerListHolder, position: Int) {
        holder.bindFollower(followers.get(position))
    }
}
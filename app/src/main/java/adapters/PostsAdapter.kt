package adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bean.rest.Post
import com.example.instagrammo.R
import holders.FollowerListHolder
import holders.PostsListHolder

class PostsAdapter (private val posts: List<Post>) : RecyclerView.Adapter<PostsListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsListHolder {
            val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_post_home,parent,false)
            return PostsListHolder(inflate)
        }

        override fun getItemCount() = posts.size

        override fun onBindViewHolder(holder: PostsListHolder, position: Int) {
            holder.bindPosts(posts.get(position))
        }

}
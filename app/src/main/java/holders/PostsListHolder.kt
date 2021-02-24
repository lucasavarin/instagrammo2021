package holders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bean.rest.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_home.view.*
import utils.extensions.CircleTransformation

class PostsListHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var posts: Post? = null

        fun bindPosts(posts: Post){
            this.posts = posts
            if(posts.profile?.picture != null){
                Picasso.get().load(posts.profile.picture).transform(CircleTransformation()).into(view.profile_image)
            }
            Picasso.get().load(posts.picture).into(view.post_image)
            //view.profile_name.text = posts.profile.name
        }

        override fun onClick(v: View?) {
            Toast.makeText(view.context, posts.toString(), Toast.LENGTH_LONG).show()
        }

}
package holders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bean.business.Follower
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_follow_home.view.*
import kotlinx.android.synthetic.main.story_item.view.*
import utils.extensions.CircleTransformation

class FollowerListHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    private var follower: Follower? = null

    fun bindFollower(follower: Follower){
        this.follower = follower
        Picasso.get().load(follower.picture).transform(CircleTransformation()).into(view.profile_image_follower)
    }

    override fun onClick(v: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}
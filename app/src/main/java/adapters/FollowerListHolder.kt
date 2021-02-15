package adapters

import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bean.Follower
import kotlinx.android.synthetic.main.story_item.view.*

class FollowerListHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    private var follower: Follower? = null

    fun bindFollower(follower: Follower){
        this.follower = follower
        /*Picasso.get().load(follower.picture).transform(CircleTransform()).into(view.followerImage)*/
        /*view.FollowerImageButton.setImageURI(Uri.EMPTY)*/                              //passare le immagini dei singoli user usando picasso
        view.blob.text = follower.name
    }

    override fun onClick(v: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}
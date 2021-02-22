package bean.rest

import android.net.Uri
import bean.business.Follower
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FollowerResponse (
    @Expose
    @SerializedName("result") val result: Boolean?,
    @Expose
    @SerializedName("payload") val payload: List<Follower>?
) : Serializable

data class FollowerProfile (
        @Expose @SerializedName("id") val id : String?,
        @Expose @SerializedName("name") val name : String?,
        @Expose @SerializedName("description") val description: String?,
        @Expose @SerializedName("picture") val picture: Uri?
) : Serializable
package bean.rest

import bean.business.Follower
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FollowerResponse (
    @Expose
    @SerializedName("result") val result: Boolean?,
    @Expose
    @SerializedName("payload") val payload: List<Follower>?
)
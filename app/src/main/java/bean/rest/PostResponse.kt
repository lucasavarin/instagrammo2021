package bean.rest

import bean.business.ResponseDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostResponse(
        @Expose @SerializedName("result") val result : Boolean?,
        @Expose @SerializedName("payload") var payload : List<Post>?
) :Serializable

data class Post (
        @Expose @SerializedName("profileId") val profileId : String?,
        @Expose @SerializedName("postId") val postId : String?,
        @Expose @SerializedName("title") val title : String?,
        @Expose @SerializedName("picture") val picture : String?,
        @Expose @SerializedName("uploadTime") val uploadTime : String?,
        @Expose @SerializedName("profile") val profile : Profile
        ) : ResponseDataModel
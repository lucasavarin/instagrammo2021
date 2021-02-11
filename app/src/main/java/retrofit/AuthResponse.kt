package retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @Expose @SerializedName("authtoken") val authtoken: String?,
    @Expose @SerializedName("profileId") val profileId: String?

    )


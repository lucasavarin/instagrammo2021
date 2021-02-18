package bean

data class ProfileBean(
        val profileId : String?,
        val name : String?,
        val description : String?,
        val picture : String?,
        val followersNumber : String?,
        val postsNumber : String?
) : DataModel {
    companion object : DataConverter<ProfileBean, Profile> {
        override fun convert(response: Profile) : ProfileBean {
            return ProfileBean(
                    response.profileId!!,
                    response.name!!,
                    response.description!!,
                    response.picture!!,
                    response.followersNumber!!,
                    response.postsNumber!!
            )
        }
    }
}

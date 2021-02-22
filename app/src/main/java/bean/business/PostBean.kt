package bean.business

import bean.rest.Post

class PostBean (
        var profileId : String,
        var postId : String,
        var title : String,
        var picture : String,
        var uploadTime : String,
        var profile : ProfileBean
) : DataModel {

    companion object : DataConverter<PostBean, Post> {
        override fun convert(response: Post) : PostBean {
            return PostBean(
                    response.profileId!!,
                    response.postId!!,
                    response.title!!,
                    response.picture!!,
                    response.uploadTime!!,
                    ProfileBean.convert(response.profile)
            )
        }
    }
}
package com.lynx.instagrammo

import com.lynx.instagrammo.bean.Post


fun loadPosts(): List<Post> {
    return listOf<Post>(
        Post("1", "1", "title", "URL"),
        Post("2", "2", "title", "URL"),
        Post("3", "3", "title", "URL"),
        Post("4", "4", "title", "URL")
    )
}

class DataSource {


}
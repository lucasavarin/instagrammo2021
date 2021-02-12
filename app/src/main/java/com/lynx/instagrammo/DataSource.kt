package com.lynx.instagrammo

import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.Post


fun loadPosts(): List<Post> {
    return listOf<Post>(
        Post("1", "1", "title", "URL"),
        Post("2", "2", "title", "URL"),
        Post("3", "3", "title", "URL"),
        Post("4", "4", "title", "URL")
    )
}

fun loadFollowers(): List<Follower> {
    return listOf<Follower>(
            Follower("1","pippo","ciao, sono pippo","URl"),
            Follower("2","pluto","ciao, sono pluto","URl"),
            Follower("3","micky","ciao, sono micky","URl"),
            Follower("4","paperino","ciao, sono paperino","URl"),
            Follower("5","pippo","ciao, sono pippo","URl"),
            Follower("6","pluto","ciao, sono pluto","URl"),
            Follower("7","micky","ciao, sono micky","URl"),
            Follower("1","pippo","ciao, sono pippo","URl"),
            Follower("2","pluto","ciao, sono pluto","URl"),
            Follower("3","micky","ciao, sono micky","URl"),
            Follower("4","paperino","ciao, sono paperino","URl"),
            Follower("5","pippo","ciao, sono pippo","URl"),
            Follower("6","pluto","ciao, sono pluto","URl"),
            Follower("7","micky","ciao, sono micky","URl")    )
}

class DataSource {

//    fun loadPosts(): List<Post> {
//        return listOf<Post>(
//            Post("1", "1", "title", "URL"),
//            Post("2", "2", "title", "URL"),
//            Post("3", "3", "title", "URL"),
//            Post("4", "4", "title", "URL")
//        )
//    }

}
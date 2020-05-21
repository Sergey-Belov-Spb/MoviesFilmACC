package com.example.moviesfilmacc.data.entity

import com.google.gson.annotations.SerializedName

class MovieItem {

    @SerializedName("git_url")
    lateinit var gitUrl: String

    constructor() {}

    constructor(gitUrl: String) {
        this.gitUrl = gitUrl
    }
}
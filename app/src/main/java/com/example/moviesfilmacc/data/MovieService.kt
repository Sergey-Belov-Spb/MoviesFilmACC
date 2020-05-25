package com.example.moviesfilmacc.data

import com.example.moviesfilmacc.data.entity.MovieItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieService {
    @GET("films")
    fun getUserRepos(): Call<List<MovieItem>>

    //@GET("users/{user}/repos")
    //fun getUserRepos(@Path("user") user: String): Call<List<MovieItem>>
}
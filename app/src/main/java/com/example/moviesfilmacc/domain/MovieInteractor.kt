package com.example.moviesfilmacc.domain

import com.example.moviesfilmacc.data.MovieRepository
import com.example.moviesfilmacc.data.MovieService
import com.example.moviesfilmacc.data.entity.MovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInteractor(private val githubService: MovieService, private val reposRepository: MovieRepository) {

    fun getRepos(username: String, callback: GetRepoCallback) {
        githubService.getUserRepos(username).enqueue(object : Callback<List<MovieItem>> {
            override fun onResponse(call: Call<List<MovieItem>>, response: Response<List<MovieItem>>) {
                if (response.isSuccessful) {
                    reposRepository.addToCache(response.body()!!)

                    callback.onSuccess(reposRepository.cachedOrFakeRepos)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetRepoCallback {
        fun onSuccess(repos: List<MovieItem>)
        fun onError(error: String)
    }
}

package com.example.moviesfilmacc.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesfilmacc.App
import com.example.moviesfilmacc.data.entity.MovieItem
import com.example.moviesfilmacc.domain.MovieInteractor

class MovieListViewModel: ViewModel() {
    private val reposLiveData = MutableLiveData<List<MovieItem>>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedRepoUrlLiveData = MutableLiveData<String>()

    private val githubInteractor = App.instance!!.movieInteractor

    val repos: LiveData<List<MovieItem>>
        get() = reposLiveData

    val error: LiveData<String>
        get() = errorLiveData

    val selectedRepoUrl: LiveData<String>
        get() = selectedRepoUrlLiveData

    fun onGetDataClick() {
        githubInteractor.getRepos("octocat", object : MovieInteractor.GetRepoCallback {
            override fun onSuccess(repos: List<MovieItem>) {
                reposLiveData.postValue(repos)
            }

            override fun onError(error: String) {
                errorLiveData.postValue(error)
            }
        })
    }

    fun onRepoSelect(repoUrl: String) {
        selectedRepoUrlLiveData.postValue(repoUrl)
    }

}
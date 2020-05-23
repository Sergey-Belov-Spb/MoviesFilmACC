package com.example.moviesfilmacc.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesfilmacc.App
import com.example.moviesfilmacc.data.entity.MovieItem
import com.example.moviesfilmacc.domain.MovieInteractor
import java.util.ArrayList

class MovieListViewModel: ViewModel() {
    private val reposLiveData = MutableLiveData<List<MovieItem>>()
    private val favoriteLiveData = MutableLiveData<List<MovieItem>>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedRepoUrlLiveData = MutableLiveData<String>()

    private val itemsFavorite = ArrayList<MovieItem>()

    private val githubInteractor = App.instance!!.movieInteractor

    val moviesAll: LiveData<List<MovieItem>>
        get() = reposLiveData

    val moviesFavorite: LiveData<List<MovieItem>>
        get() = favoriteLiveData

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

    fun onRepoSelect(item: MovieItem, addToFavotite: Boolean) {
        if (addToFavotite){
            Log.d("MovieListViewModel","AddToFavorite")
            if (item.favorite == true) {
                item.favorite = false
                /*listOf(favoriteLiveData)
                favoriteLiveData.value*/
            }
            else {item.favorite = true }

            if ((item.favorite == true)&&(itemsFavorite.indexOf(item)==-1)) { itemsFavorite.add(item) }
            else {
                itemsFavorite.remove(item)
            }

            favoriteLiveData.postValue(reposLiveData.value)
            val movieFavorite = favoriteLiveData.value

        }
        else {
            selectedRepoUrlLiveData.postValue(item.title)
        }
    }

}
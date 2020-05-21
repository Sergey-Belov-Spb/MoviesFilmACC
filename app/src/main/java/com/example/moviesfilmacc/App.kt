package com.example.moviesfilmacc

import android.app.Application
import com.example.moviesfilmacc.data.MovieRepository
import com.example.moviesfilmacc.data.MovieService
import com.example.moviesfilmacc.domain.MovieInteractor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(){

    lateinit var movieService: MovieService
    // lateinit var githubReposUpdater: GithubReposUpdater
    lateinit var movieInteractor: MovieInteractor
    var reposRepository = MovieRepository()

    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
        initInteractor()
    }

    private fun initInteractor() {
        movieInteractor = MovieInteractor(movieService, reposRepository)
    }
    private fun initRetrofit() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        movieService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(MovieService::class.java!!)

        ///githubReposUpdater = GithubReposUpdater(githubService)
    }


    companion object{
        var instance : App? = null
            private set
    }
}
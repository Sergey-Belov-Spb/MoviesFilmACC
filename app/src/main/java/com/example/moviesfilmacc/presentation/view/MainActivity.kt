package com.example.moviesfilmacc.presentation.view



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.moviesfilmacc.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActiviry"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtonListener()
        openAllMoviesList()
    }

    fun initButtonListener() {
        findViewById<BottomNavigationView>(R.id.navigationBottom).setOnNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.action_allMovies ->{
                    Log.d(TAG,"action_allMovies")
                    openAllMoviesList()
                }
                R.id.action_favoriteMovies ->{
                    Log.d(TAG,"action_favoriteMovies")
                    openFavoriteMoviesList()
                }
            }
            false
        }
    }


    private fun openAllMoviesList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,MovieListFragment(), MovieListFragment.TAG  )
            //.addToBackStack("Main")
            .commit()
    }

    private fun openFavoriteMoviesList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,MovieListFavoriteFragment(), MovieListFavoriteFragment.TAG  )
            //.addToBackStack("Main")
            .commit()
    }


}

/*class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/

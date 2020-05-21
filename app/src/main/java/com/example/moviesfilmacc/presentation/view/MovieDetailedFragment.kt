package com.example.moviesfilmacc.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviesfilmacc.R
import com.example.moviesfilmacc.presentation.viewmodel.MovieListViewModel

class MovieDetailedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = ViewModelProviders.of(activity!!).get<MovieListViewModel>(MovieListViewModel::class.java!!)
        viewModel.selectedRepoUrl.observe(this.viewLifecycleOwner, Observer<String> { s -> view.findViewById<TextView>(R.id.textView).setText(s) })
    }
}
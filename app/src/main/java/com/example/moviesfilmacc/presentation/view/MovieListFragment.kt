package com.example.moviesfilmacc.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.example.moviesfilmacc.R
import com.example.moviesfilmacc.data.entity.MovieItem
import com.example.moviesfilmacc.presentation.viewmodel.MovieListViewModel
import java.util.ArrayList
//import java.util.Observer

class MovieListFragment : Fragment() {
    private var viewModel: MovieListViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: ReposAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()

        viewModel = ViewModelProviders.of(activity!!).get(MovieListViewModel::class.java!!)
        viewModel!!.repos.observe(this.viewLifecycleOwner, Observer<List<MovieItem>> { repos -> adapter!!.setItems(repos)})
        viewModel!!.error.observe(this.viewLifecycleOwner, Observer<String> { error -> Toast.makeText(context, error, Toast.LENGTH_SHORT).show() })

        view.findViewById<View>(R.id.getDataBtn).setOnClickListener { v -> viewModel!!.onGetDataClick() }

    }

    private fun initRecycler() {
        adapter = ReposAdapter(LayoutInflater.from(context), object : ReposAdapter.OnRepoSelectedListener {
            override fun onRepoSelect(url: String) {
                viewModel!!.onRepoSelect(url)
            }
        })
        recyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView!!.adapter = adapter
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(url: String) {
            (itemView as TextView).text = url
        }
    }

    class ReposAdapter(private val inflater: LayoutInflater, private val listener: OnRepoSelectedListener) : RecyclerView.Adapter<RepoViewHolder>() {
        private val items = ArrayList<MovieItem>()

        fun setItems(repos: List<MovieItem>) {
            items.clear()
            items.addAll(repos)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            return RepoViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            holder.bind(items[position].gitUrl)
            holder.itemView.setOnClickListener { v -> listener.onRepoSelect(items[position].gitUrl) }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        interface OnRepoSelectedListener {
            fun onRepoSelect(url: String)
        }
    }
}
package com.example.movieupdate.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieupdate.Adapter.MoviesAdapter
import com.example.movieupdate.Client.ApiClient
import com.example.movieupdate.Interface.ApiInterface
import com.example.movieupdate.Modal.MovieModal
import com.example.movieupdate.Modal.ResultsItem
import com.example.movieupdate.databinding.FragmentNowPlayingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlaying : Fragment() {

    var page = 1
    var adapter = MoviesAdapter()
    var list = ArrayList<ResultsItem>()
    lateinit var binding: FragmentNowPlayingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNowPlayingBinding.inflate(layoutInflater)

        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                callApi(page)
            }
        })

        callApi(page)
        return binding.root
    }

    private fun callApi(page: Int) {

        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getNowPlaying(this.page, "bearer ${ApiClient.Token}")
            .enqueue(object : Callback<MovieModal> {
                override fun onResponse(call: Call<MovieModal>, response: Response<MovieModal>) {

                    if (response.isSuccessful) {

                        var movieList = response.body()?.results
                        list.addAll(movieList as ArrayList<ResultsItem>)

                        adapter.setmovies(list)
                        binding.RcvMovie.layoutManager = LinearLayoutManager(context)
                        binding.RcvMovie.adapter = adapter

                    }
                }

                override fun onFailure(call: Call<MovieModal>, t: Throwable) {

                }
            })
    }



    }

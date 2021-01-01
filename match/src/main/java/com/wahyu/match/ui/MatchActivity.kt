package com.wahyu.match.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyu.core.data.source.Resource
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.match.R
import com.wahyu.match.databinding.ActivityMatchBinding
import com.wahyu.match.di.MatchModule
import com.wahyu.myfootball.ui.standing.StandingAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MatchActivity : AppCompatActivity() {

    private val viewModel: MatchViewModel by viewModel()
    private lateinit var binding: ActivityMatchBinding
    private val matchAdapter = MatchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(MatchModule.viewModelModule)
        getMatchByLeague(2, "2018-08-11")
    }

    private fun getMatchByLeague(id: Int, date: String){
        viewModel.getMatchByLeague(id, date).observeForever {
            when (it) {
                is Resource.Loading -> setupLoading(true)
                is Resource.Success -> successGetMatch(it)
                is Resource.Error -> errorGetMatch(it)

                else -> setupLoading(false)
            }
            setupAdapterMatch()
        }
    }

    private fun setupAdapterMatch() {
        with(binding.rvMatch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
    }

    private fun successGetMatch(response: Resource<out List<Match>>) {
        matchAdapter.setData(response.data)
        setupLoading(false)
    }

    private fun setupLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun errorGetMatch(response: Resource<out List<Match>>) {
        setupError(response.message.toString())
        setupLoading(false)
    }

    private fun setupError(response: String){
        binding.viewError.root.visibility = View.VISIBLE
        binding.viewError.tvError.text = response
    }
}
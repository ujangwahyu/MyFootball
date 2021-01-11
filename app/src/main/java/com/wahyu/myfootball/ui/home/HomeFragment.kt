package com.wahyu.myfootball.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.HomeFragmentBinding
import com.wahyu.myfootball.ui.home.adapter.MatchAdapter
import com.wahyu.myfootball.ui.home.adapter.LeagueAdapter
import com.wahyu.myfootball.utils.sheetBehavior
import kotlinx.android.synthetic.main.calendar_item.view.*
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var behavior: BottomSheetBehavior<*>
    private val leagueAdapter = LeagueAdapter()
    private val matchAdapter = MatchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataLeague("england", "2020")
        getDataMatch(2)
        setupBottomsheet()
        setupComponent()
    }

    private fun getDataLeague(country: String, season: String){
        homeViewModel.getLeague(country, season).observeForever {
            when (it) {
                is Result.Loading -> setupLoading(true)
                is Result.Success -> successGetLeague(it)
                is Result.Error -> errorGetLeague(it)

                else -> setupLoading(false)
            }
            setupAdapterLeague()
        }
    }

    private fun getDataMatch(id: Int) {
        homeViewModel.getMatchByLeague(id, "").observeForever {
            when (it) {
                is Result.Loading -> setupLoading(true)
                is Result.Success -> successGetMatch(it)
                is Result.Error -> errorGetMatch(it)

                else -> setupLoading(false)
            }
            setupAdapterMatch()
        }
    }

    private fun setupAdapterLeague() {
        with(binding.rvLeague) {
            layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL , false)
            adapter = leagueAdapter
        }
    }

    private fun setupAdapterMatch() {
        with(binding.rvMatch) {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
    }

    private fun successGetLeague(response: Result<List<League>>) {
        leagueAdapter.setData(response.data)
        setupLoading(false)
    }

    private fun successGetMatch(response: Result<List<Match>>) {
        matchAdapter.setData(response.data)
        setupLoading(false)
    }

    private fun errorGetLeague(response: Result<List<League>>) {
        setupError(response.message.toString())
        setupLoading(false)
    }

    private fun errorGetMatch(response: Result<List<Match>>) {
        setupError(response.message.toString())
        setupLoading(false)
    }

    private fun setupLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupError(response: String){
        binding.tvError.visibility = View.GONE
        binding.tvError.text = response
    }

    private fun setupComponent() {
        binding.imgFavorite.setOnClickListener {
            val uri = Uri.parse("myfootball://match")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height/2
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.wahyu.myfootball.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import com.wahyu.core.data.source.Resource
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.HomeFragmentBinding
import com.wahyu.myfootball.ui.home.adapter.LastMatchAdapter
import com.wahyu.myfootball.ui.home.adapter.TeamAdapter
import com.wahyu.myfootball.ui.home.adapter.TodayMatchAdapter
import com.wahyu.myfootball.ui.home.adapter.UpcomingMatchAdapter
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
    private val teamAdapter = TeamAdapter()

    private val calendar = Calendar.getInstance()
    private var currentMonth = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()
        setupViewModel()
        setupComponent()
        setupCalendar()
    }

    private fun setupViewModel(){
//        homeViewModel.team.observe(viewLifecycleOwner) {
//            when(it) {
//                is Resource.Loading -> setupLoading(true)
//                is Resource.Success -> successGetTeam(it)
//                is Resource.Error -> errorGetTeam(it)
//                else -> setupLoading(false)
//            }
//        }
//        setupAdapterTeam()

    }

//    private fun setupAdapterTeam() {
//        with(binding.rvTeam) {
//            layoutManager = StaggeredGridLayoutManager(2,
//                    StaggeredGridLayoutManager.HORIZONTAL)
//            adapter = teamAdapter
//            setHasFixedSize(true)
//        }
//    }

    private fun successGetTeam(response: Resource<out List<Team>>) {
        teamAdapter.setData(response.data)
        setupLoading(false)
    }

    private fun errorGetTeam(response: Resource<out List<Team>>) {
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

    }

    private fun setupComponent() {

    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height/2
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setupCalendar() {
// set current date to calendar and current month to currentMonth variable
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]

        // calendar view manager is responsible for our displaying logic
        val myCalendarViewManager = object :
                CalendarViewManager {
            override fun setCalendarViewResourceId(
                    position: Int,
                    date: Date,
                    isSelected: Boolean
            ): Int {
                // set date to calendar according to position where we are
                val cal = Calendar.getInstance()
                cal.time = date
                // if item is selected we return this layout items
                // in this example. monday, wednesday and friday will have special item views and other days
                // will be using basic item view
                return if (isSelected)
                    R.layout.selected_calendar_item
                else
                    R.layout.first_special_calendar_item
            }

            override fun bindDataToCalendarView(
                    holder: SingleRowCalendarAdapter.CalendarViewHolder,
                    date: Date,
                    position: Int,
                    isSelected: Boolean
            ) {
                // using this method we can bind data to calendar view
                // good practice is if all views in layout have same IDs in all item views
                holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
                holder.itemView.tv_day_calendar_item.text = DateUtils.getDay3LettersName(date)

            }
        }

        // using calendar changes observer we can track changes in calendar
        val myCalendarChangesObserver = object :
                CalendarChangesObserver {
            // you can override more methods, in this example we need only this one
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                binding.tvDate.text = "${DateUtils.getMonthName(date)}, ${DateUtils.getDayNumber(date)} "
                binding.tvDay.text = DateUtils.getDayName(date)
                super.whenSelectionChanged(isSelected, position, date)
            }
        }

        // selection manager is responsible for managing selection
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                // set date to calendar according to position
                val cal = Calendar.getInstance()
                cal.time = date
                // in this example sunday and saturday can't be selected, others can
                return when (cal[Calendar.DAY_OF_WEEK]) {
                    Calendar.SATURDAY -> true
                    Calendar.SUNDAY -> true
                    else -> true
                }
            }
        }

        // here we init our calendar, also you can set more properties if you haven't specified in XML layout
        val singleRowCalendar = singleCalendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            includeCurrentDate = true
            setDates(getFutureDatesOfCurrentMonth())
            init()
        }

        binding.btnRight.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfNextMonth())
        }

        binding.btnLeft.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfPreviousMonth())
        }
    }

    private fun getDatesOfNextMonth(): List<Date> {
        currentMonth++ // + because we want next month
        if (currentMonth == 12) {
            // we will switch to january of next year, when we reach last month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0 // 0 == january
        }
        return getDates(mutableListOf())
    }

    private fun getDatesOfPreviousMonth(): List<Date> {
        currentMonth-- // - because we want previous month
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        return getDates(mutableListOf())
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.wahyu.myfootball.ui.standing

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wahyu.core.data.source.Result
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.StandingFragmentBinding
import com.wahyu.myfootball.utils.sheetBehavior
import org.koin.android.viewmodel.ext.android.viewModel

class StandingFragment : Fragment() {

    private val standingViewModel: StandingViewModel by viewModel()
    private var _binding: StandingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StandingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()

        if (activity != null) {
            val standingAdapter = StandingAdapter()

            standingViewModel.standing.observe(viewLifecycleOwner) { standing ->
                when(standing) {
                    is Result.Loading -> binding.lottieLoading.visibility = View.VISIBLE
                    is Result.Success -> {
                        binding.lottieLoading.visibility = View.GONE
                        standingAdapter.setData(standing.data!![0])
                    }
                    is Result.Error -> {
                        binding.lottieLoading.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = standing.message ?: getString(R.string.something_wrong)
                    }
                }
            }

            with(binding.rvStanding) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = standingAdapter
            }
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


    companion object {
        fun newInstance() = StandingFragment()
    }

}
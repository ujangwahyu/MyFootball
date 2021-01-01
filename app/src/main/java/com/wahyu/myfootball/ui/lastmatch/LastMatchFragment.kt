package com.wahyu.myfootball.ui.lastmatch

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wahyu.myfootball.databinding.LastMatchFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LastMatchFragment : Fragment() {

    private val lastMatchViewModel: LastMatchViewModel by viewModel()
    private var _binding: LastMatchFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LastMatchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = LastMatchFragment()
    }

}
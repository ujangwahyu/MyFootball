package com.wahyu.myfootball.ui.topscore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahyu.myfootball.R

class TopScoreFragment : Fragment() {

    companion object {
        fun newInstance() = TopScoreFragment()
    }

    private lateinit var viewModel: TopScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_score_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.wahyu.myfootball.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.core.domain.model.todaymatch.TodayMatch
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ItemTodayMatchBinding
import com.wahyu.myfootball.databinding.ItemUpcomingMatchBinding
import java.util.ArrayList

class UpcomingMatchAdapter : RecyclerView.Adapter<UpcomingMatchAdapter.ListViewHolder>() {

    private var data = ArrayList<UpcomingMatch>()
    var onItemClick: ((UpcomingMatch) -> Unit)? = null

    fun setData(newListData: List<UpcomingMatch>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_upcoming_match, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUpcomingMatchBinding.bind(itemView)
        fun bind(data: UpcomingMatch) {
            with(binding) {
                Glide.with(itemView.context).load(data.homeTeam.logo).into(imgTeamHome)
                Glide.with(itemView.context).load(data.awayTeam.logo).into(imgTeamAway)
                binding.tvTeamHome.text = data.homeTeam.team_name
                binding.tvScoreTeamHome.text = data.goalsHomeTeam.toString()
                binding.tvTeamAway.text = data.awayTeam.team_name
                binding.tvScoreTeamAway.text = data.goalsAwayTeam.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}
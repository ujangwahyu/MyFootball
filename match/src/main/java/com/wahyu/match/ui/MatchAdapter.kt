package com.wahyu.match.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.match.R
import com.wahyu.match.databinding.ItemMatchBinding

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ListViewHolder>() {

    private var data = ArrayList<Match>()
    var onItemClick: ((Match) -> Unit)? = null

    fun setData(newListData: List<Match>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_match, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMatchBinding.bind(itemView)
        fun bind(data: Match) {
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
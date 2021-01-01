package com.wahyu.myfootball.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ItemTodayMatchBinding
import java.util.ArrayList

class TodayMatchAdapter : RecyclerView.Adapter<TodayMatchAdapter.ListViewHolder>() {

    private var data = ArrayList<TodayMatch>()
    var onItemClick: ((TodayMatch) -> Unit)? = null

    fun setData(newListData: List<TodayMatch>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_today_match, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTodayMatchBinding.bind(itemView)
        fun bind(data: TodayMatch) {
            with(binding) {
                Glide.with(itemView.context).load(data.homeTeam.logo).into(imgTeamHome)
                Glide.with(itemView.context).load(data.awayTeam.logo).into(imgTeamAway)
                binding.tvTeamHome.text = data.homeTeam.team_name
                binding.tvTeamAway.text = data.awayTeam.team_name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}
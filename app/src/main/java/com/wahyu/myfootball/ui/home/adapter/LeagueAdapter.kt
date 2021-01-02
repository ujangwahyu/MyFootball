package com.wahyu.myfootball.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ItemLeagueBinding
import java.util.ArrayList

class LeagueAdapter : RecyclerView.Adapter<LeagueAdapter.ListViewHolder>() {

    private var data = ArrayList<League>()
    var onItemClick: ((League) -> Unit)? = null

    fun setData(newListData: List<League>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_league, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLeagueBinding.bind(itemView)
        fun bind(data: League) {
            with(binding) {
                Glide.with(itemView.context).load(data.logo).into(imgLogo)
                binding.rvLeaguename.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}
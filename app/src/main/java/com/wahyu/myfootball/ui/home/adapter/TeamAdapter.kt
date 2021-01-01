package com.wahyu.myfootball.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ItemTeamBinding
import java.util.ArrayList

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ListViewHolder>() {

    private var data = ArrayList<Team>()
    var onItemClick: ((Team) -> Unit)? = null

    fun setData(newListData: List<Team>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_team, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTeamBinding.bind(itemView)
        fun bind(data: Team) {
            with(binding) {
                Glide.with(itemView.context).load(data.logo).into(imgTeam)
                binding.tvTeamName.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}
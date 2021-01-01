package com.wahyu.myfootball.ui.standing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ItemStandingBinding
import java.util.ArrayList

class StandingAdapter : RecyclerView.Adapter<StandingAdapter.ListViewHolder>() {

    private var data = ArrayList<Standing>()
    var onItemClick: ((Standing) -> Unit)? = null

    fun setData(newListData: List<Standing>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_standing, parent, false)
            )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemStandingBinding.bind(itemView)
        fun bind(data: Standing) {
            with(binding) {
                Glide.with(itemView.context).load(data.logo).into(imgTeam)
                binding.tvNumber.text = data.rank.toString()
                binding.tvTeamName.text = data.teamName
                binding.tvWin.text = data.all.win.toString()
                binding.tvDraw.text = data.all.draw.toString()
                binding.tvLose.text = data.all.lose.toString()
                binding.tvPts.text = data.points.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}
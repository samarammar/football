package com.glamera.football.presentation.competitions.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glamera.football.databinding.ItemCompetitionsBinding
import com.glamera.football.domain.entity.CompetitionItem
import kotlinx.coroutines.flow.*

class CompetitionsAdapter constructor(
    private val context: Context? = null
) : RecyclerView.Adapter<CompetitionsAdapter.CompetitionsViewHolder>() {

    private val competitionItem: ArrayList<CompetitionItem> = ArrayList()


    class CompetitionsViewHolder(binding: ItemCompetitionsBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.tvCompetitionValue
        val code = binding.tvCompetitionCodeValue
        val date_from = binding.tvFromValue
        val to = binding.tvToValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        val viewBinding = ItemCompetitionsBinding.inflate(LayoutInflater.from(parent.context))
        return CompetitionsViewHolder(viewBinding)



    }

    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        competitionItem[position].also {
            holder.name.text = it.name
            holder.code.text = it.code ?: "-"
            holder.date_from.text = it.currentSeason.startDate
            holder.to.text = it.currentSeason.endDate
        }
    }
    override fun getItemCount(): Int = competitionItem.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(competition: List<CompetitionItem>) {
        competitionItem.run {
            clear()
            addAll(competition)
            notifyDataSetChanged()
        }
    }


}

package com.portal.tftteambuilderproject.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.ItemChampsBinding
import com.portal.tftteambuilderproject.data.ChampionItem
import com.portal.tftteambuilderproject.utilities.helper.Util

@SuppressLint("NotifyDataSetChanged")
class SelectedChampsAdapter(
    private val context: Context,
    private val removeChamps: (List<ChampionItem>) -> Unit

) : RecyclerView.Adapter<SelectedChampsAdapter.SelectedChampsViewHolder>() {
    private var selectedChampions: MutableList<ChampionItem> = mutableListOf()

    inner class SelectedChampsViewHolder(private val binding: ItemChampsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChampionItem) {
            binding.apply {
                val champion = Util.Champion.values().find { it.championName == item.name }
                if (champion != null) {
                    ivChamp.setImageResource(champion.imagePath)
                    tvChamp.setText(champion.championName)
                    ivChamp.setOnClickListener {
                        removeChamp(item)
                    }
                }


                ivChamp.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        setCardBackgroundColorByCost(item.cost)
                    )
                )

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SelectedChampsViewHolder {
        return SelectedChampsViewHolder(
            ItemChampsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SelectedChampsViewHolder, position: Int
    ) {
        val championItem = selectedChampions[position]
        holder.bind(championItem)
    }

    override fun getItemCount(): Int {
        return selectedChampions.size
    }

    private fun setCardBackgroundColorByCost(cost: Int): Int {
        val colorResId = when (cost) {
            ChampsAdapter.ONE_COST -> R.color.one_cost
            ChampsAdapter.TWO_COST -> R.color.two_cost
            ChampsAdapter.THREE_COST -> R.color.three_cost
            ChampsAdapter.FOUR_COST -> R.color.four_cost
            ChampsAdapter.FIVE_COST -> R.color.five_cost
            else -> R.color.black
        }
        return colorResId
    }

    private fun removeChamp(championItem: ChampionItem) {
        if (selectedChampions.contains(championItem)) {
            selectedChampions.remove(championItem)
        }
        removeChamps.invoke(selectedChampions)
        notifyDataSetChanged()
    }

    fun updateSelectedChampions(champions: List<ChampionItem>) {
        selectedChampions.clear()
        selectedChampions.addAll(champions)
        notifyDataSetChanged()
    }
}
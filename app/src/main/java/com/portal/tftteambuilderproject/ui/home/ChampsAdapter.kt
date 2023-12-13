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
import com.portal.tftteambuilderproject.utilities.customviews.TftCustomToast
import com.portal.tftteambuilderproject.utilities.helper.Util

@SuppressLint("NotifyDataSetChanged")

class ChampsAdapter(
    private val context: Context,
    private val selectedChampionsChanged: (List<ChampionItem>) -> Unit

) :
    RecyclerView.Adapter<ChampsAdapter.ChampsViewHolder>() {
    private var selectedChampions: MutableList<ChampionItem> = mutableListOf()
    private var items: MutableList<ChampionItem> = mutableListOf()

    fun addItem(newItems: List<ChampionItem>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun updateSelectedChampions(champions: List<ChampionItem>) {
        selectedChampions.clear()
        selectedChampions.addAll(champions)
        notifyDataSetChanged()
    }

    inner class ChampsViewHolder(private val binding: ItemChampsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChampionItem) {
            binding.apply {
                val champion = Util.Champion.values().find { it.championName == item.name }
                if (champion != null) {
                    ivChamp.setImageResource(champion.imagePath)
                    tvChamp.setText(champion.championName)
                    ivChamp.setOnClickListener {
                        toggleSelection(item)
                    }
                    ivChamp.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            setCardBackgroundColorByCost(item.cost)
                        )
                    )

                    ivChamp.setOnLongClickListener { view ->

                        val matchingOrigins =
                            Util.Origin.values().filter { it.feature in item.origin }
                        if (matchingOrigins.isNotEmpty()) {
                            TftCustomToast.showToast(
                                context,
                                matchingOrigins.joinToString("\n") { it.feature },
                                view
                            )
                        }

                        true
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ChampsViewHolder {
        return ChampsViewHolder(
            ItemChampsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }


    override fun onBindViewHolder(holder: ChampsAdapter.ChampsViewHolder, position: Int) {
        val championItem = items[position]
        holder.bind(championItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun setCardBackgroundColorByCost(cost: Int): Int {
        val colorResId = when (cost) {
            ONE_COST -> R.color.one_cost
            TWO_COST -> R.color.two_cost
            THREE_COST -> R.color.three_cost
            FOUR_COST -> R.color.four_cost
            FIVE_COST -> R.color.five_cost
            else -> R.color.black
        }
        return colorResId
    }


    private fun toggleSelection(championItem: ChampionItem) {
        if (selectedChampions.contains(championItem)) {
            selectedChampions.remove(championItem)
        } else {
            selectedChampions.add(championItem)
        }
        selectedChampionsChanged.invoke(selectedChampions)
        notifyDataSetChanged()
    }

    companion object {
        const val ONE_COST = 1
        const val TWO_COST = 2
        const val THREE_COST = 3
        const val FOUR_COST = 4
        const val FIVE_COST = 5
    }
}
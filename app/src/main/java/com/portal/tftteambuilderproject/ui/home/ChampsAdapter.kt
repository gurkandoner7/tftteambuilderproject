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

class ChampsAdapter(
    private val context: Context,
    private val championClicked: (ChampionItem) -> Unit
) :
    RecyclerView.Adapter<ChampsAdapter.ChampsViewHolder>() {
    private var items: MutableList<ChampionItem> = mutableListOf()


    fun addItem(newItems: List<ChampionItem>) {
        items.addAll(newItems)
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
                        championClicked.invoke(item)
                    }

                    ivChamp.setBackgroundColor(ContextCompat.getColor(context,setCardBackgroundColorByCost(item.cost)))
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
            1 -> R.color.one_cost
            2 -> R.color.two_cost
            3 -> R.color.three_cost
            4 -> R.color.four_cost
            5 -> R.color.five_cost
            else -> R.color.black
        }
        return colorResId
    }
}
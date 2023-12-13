package com.portal.tftteambuilderproject.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.ItemOriginBinding
import com.portal.tftteambuilderproject.utilities.helper.Util

@SuppressLint("NotifyDataSetChanged")
class OriginAdapter(
    private val context: Context,
) : RecyclerView.Adapter<OriginAdapter.OriginViewHolder>() {
    private var originCounts: Map<String, Int> = emptyMap()
    private var items: MutableList<String> = mutableListOf()
    private var originList: List<Pair<String, Int>> = emptyList()

    fun updateItems(newItems: List<String>) {
        originCounts = newItems.groupingBy { it }.eachCount()
        items.clear()
        items.addAll(originCounts.keys)
        originList = originCounts.toList()
            .sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenByDescending { origin ->
                val originEnum = Util.Origin.values().find { it.feature == origin.first }
                originEnum?.triggerFrequency?.indexOfFirst { origin.second >= it } ?: -1
            })
        notifyDataSetChanged()
    }

    inner class OriginViewHolder(private val binding: ItemOriginBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            val originItem = originList.find { it.first == item }
            val originEnum = Util.Origin.values().find { it.feature == originItem?.first }
            if (originEnum != null) {
                val feature = originEnum.feature
                val triggerFrequency = originEnum.triggerFrequency
                val count = originItem?.second
                if (count != null && triggerFrequency.size != 1) {
                    var level = 0
                    for (freq in triggerFrequency) {
                        if (count >= freq) {
                            level++
                        } else {
                            break
                        }
                    }
                    val levelValue = triggerFrequency[level]
                    Log.d("OriginAdapter", "Origin: $feature, Level: $levelValue")
                    binding.apply {
                        tvOriginCount.setText("$count/$levelValue")
                        tvOriginName.setText(feature)
                        ivOrigin.setImageResource(originEnum.imagePath)
                        ivOrigin.setBackgroundColor(
                            ContextCompat.getColor(
                                context, getLevelColor(level)
                            )
                        )
                    }

                } else {
                    binding.apply {
                        tvOriginName.setText(feature)
                        ivOrigin.setImageResource(originEnum.imagePath)
                        tvOriginCount.setText("$count")
                        ivOrigin.setBackgroundColor(
                            ContextCompat.getColor(
                                context, R.color.level_unique
                            )
                        )
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OriginViewHolder {
        return OriginViewHolder(
            ItemOriginBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }


    override fun onBindViewHolder(holder: OriginAdapter.OriginViewHolder, position: Int) {
        val originItem = originList[position]
        val item = originItem.first
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return originList.size
    }

    private fun getLevelColor(level: Int): Int {
        return when (level) {
            Level.ZERO.ordinal -> R.color.level_zero
            Level.ONE.ordinal -> R.color.level_one
            Level.TWO.ordinal -> R.color.level_two
            Level.THREE.ordinal -> R.color.level_three
            Level.FOUR.ordinal -> R.color.level_four
            else -> R.color.level_unique
        }
    }

    enum class Level {
        ZERO, ONE, TWO, THREE, FOUR,
    }
}
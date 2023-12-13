package com.portal.tftteambuilderproject.utilities.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatEditText
import com.portal.tftteambuilder.R
import com.portal.tftteambuilderproject.data.ChampionItem
import com.portal.tftteambuilderproject.ui.home.ChampsAdapter
import com.portal.tftteambuilderproject.utilities.customviews.TftCustomTextView

fun RadioGroup.setFilterListeners(
    champsAdapter: ChampsAdapter,
    championItem: List<ChampionItem>,
    tvClearFilters: TftCustomTextView
) {
    this.setOnCheckedChangeListener { group, checkedId ->
        val filteredChampions = when (checkedId) {
            R.id.rbOne -> championItem.filter { it.cost == 1 }
            R.id.rbTwo -> championItem.filter { it.cost == 2 }
            R.id.rbThree -> championItem.filter { it.cost == 3 }
            R.id.rbFour -> championItem.filter { it.cost == 4 }
            R.id.rbFive -> championItem.filter { it.cost == 5 }
            else -> emptyList()
        }

        champsAdapter.addItem(filteredChampions)

        tvClearFilters.visibility = if (checkedId != View.NO_ID) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    tvClearFilters.setOnClickListener {
        this.clearCheck()
        champsAdapter.addItem(championItem)
        tvClearFilters.visibility = View.INVISIBLE
    }
}

fun AppCompatEditText.setSearchTextChangeListener(
    championItem: List<ChampionItem>, champsAdapter: ChampsAdapter
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchQueries = s?.trim()?.split("\\s*,\\s*".toRegex()) ?: emptyList()
            val filteredChampions = championItem.filter { champion ->
                searchQueries.all { query ->
                    champion.matchesSearchQuery(query)
                }
            }

            champsAdapter.addItem(filteredChampions)
        }        override fun afterTextChanged(s: Editable?) {
        }
    })
}
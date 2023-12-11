package com.portal.tftteambuilderproject.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.FragmentHomeBinding
import com.portal.tftteambuilderproject.compose.BaseFragment
import com.portal.tftteambuilderproject.compose.viewBinding
import com.portal.tftteambuilderproject.data.ChampionItem
import com.portal.tftteambuilderproject.data.collectOrigins
import kotlinx.coroutines.launch
import org.json.JSONArray

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var champsAdapter: ChampsAdapter
    private lateinit var originAdapter: OriginAdapter

    override fun observeVariables() {
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.championList.collect {
                    champsAdapter.addItem(it)
                }
            }
        }

    }

    override fun initUI(savedInstanceState: Bundle?) {
        champsAdapter = ChampsAdapter(requireContext(),
            selectedChampionsChanged = { championList ->
                homeViewModel.setSelectedOriginList(championList.collectOrigins())
                originAdapter.updateItems(homeViewModel.selectedOriginList.value)
            })

        binding.rvChamps.adapter = champsAdapter
        homeViewModel.setChampionList(readChampionsFromJson())
        gridLayoutSize()
        originAdapter = OriginAdapter(requireContext())
        binding.rvOrigin.adapter = originAdapter
    }

    private fun gridLayoutSize() {
        val layoutManager = GridLayoutManager(context, 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        binding.rvChamps.layoutManager = layoutManager
        binding.rvChamps.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT

    }

    private fun readChampionsFromJson(): List<ChampionItem> {
        val champions = mutableListOf<ChampionItem>()
        try {
            val jsonArray = JSONArray(resources.openRawResource(R.raw.champions).bufferedReader()
                .use { it.readText() })

            for (i in 0 until jsonArray.length()) {
                val championJson = jsonArray.getJSONObject(i)
                val origin = mutableListOf<String>()

                for (j in 0 until championJson.getJSONArray("origin").length()) {
                    origin.add(championJson.getJSONArray("origin").getString(j))
                }

                val championItem = ChampionItem(
                    championJson.getString("name"), championJson.getInt("cost"), origin
                )
                champions.add(championItem)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return champions
    }
}
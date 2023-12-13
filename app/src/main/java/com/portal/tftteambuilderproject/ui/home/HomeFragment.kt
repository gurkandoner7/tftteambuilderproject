package com.portal.tftteambuilderproject.ui.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.FragmentHomeBinding
import com.portal.tftteambuilderproject.compose.BaseFragment
import com.portal.tftteambuilderproject.compose.viewBinding
import com.portal.tftteambuilderproject.data.ChampionItem
import com.portal.tftteambuilderproject.data.collectOrigins
import com.portal.tftteambuilderproject.utilities.extensions.readChampionsFromJson
import com.portal.tftteambuilderproject.utilities.extensions.setFilterListeners
import com.portal.tftteambuilderproject.utilities.extensions.setGridLayoutSize
import com.portal.tftteambuilderproject.utilities.extensions.setSearchTextChangeListener
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var champsAdapter: ChampsAdapter
    private lateinit var originAdapter: OriginAdapter
    private lateinit var selectedChampsAdapter: SelectedChampsAdapter

    override fun observeVariables() {
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.championList.collect { championItem ->
                    champsAdapter.addItem(championItem)
                    binding.apply {
                        radioGroupFilter.setFilterListeners(champsAdapter, championItem, binding.tvClearFilters)
                        etFilter.setSearchTextChangeListener(championItem, champsAdapter)
                    }
                }
            }
        }

    }

    override fun initUI(savedInstanceState: Bundle?) {

        champsAdapter = ChampsAdapter(requireContext(), selectedChampionsChanged = { championList ->
            updateList(championList)
            selectedChampsAdapter.updateSelectedChampions(championList)
        })
        selectedChampsAdapter =
            SelectedChampsAdapter(requireContext(), removeChamps = { championList ->
                updateList(championList)
                champsAdapter.updateSelectedChampions(championList)
            })
        binding.rvSelectedChamps.adapter = selectedChampsAdapter


        binding.rvChamps.adapter = champsAdapter
        homeViewModel.setChampionList(requireContext().readChampionsFromJson())
        binding.rvChamps.setGridLayoutSize(6)
        binding.rvOrigin.setGridLayoutSize(6)
        originAdapter = OriginAdapter(requireContext())
        binding.rvOrigin.adapter = originAdapter

    }


    private fun updateList(championList: List<ChampionItem>) {
        homeViewModel.setSelectedOriginList(championList.collectOrigins())
        originAdapter.updateItems(homeViewModel.selectedOriginList.value)
    }
}
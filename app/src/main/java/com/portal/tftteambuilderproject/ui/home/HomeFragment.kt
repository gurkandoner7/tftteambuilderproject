package com.portal.tftteambuilderproject.ui.home

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.portal.tftteambuilder.R
import com.portal.tftteambuilder.databinding.FragmentHomeBinding
import com.portal.tftteambuilderproject.compose.BaseFragment
import com.portal.tftteambuilderproject.compose.viewBinding
import com.portal.tftteambuilderproject.data.ChampionItem
import kotlinx.coroutines.launch
import org.json.JSONArray

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var champsAdapter: ChampsAdapter


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
            championClicked = {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        })
        binding.rvChamps.adapter = champsAdapter
        homeViewModel.setChampionList(readChampionsFromJson())
        gridLayoutSize()
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
            val inputStream = resources.openRawResource(R.raw.champions)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val championJson = jsonArray.getJSONObject(i)
                val championName = championJson.getString("name")
                val cost = championJson.getInt("cost")
                val originArray = championJson.getJSONArray("origin")
                val origin = mutableListOf<String>()

                for (j in 0 until originArray.length()) {
                    val originValue = originArray.getString(j)
                    origin.add(originValue)
                }

                val championItem = ChampionItem(championName, cost, origin)
                champions.add(championItem)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return champions
    }
}
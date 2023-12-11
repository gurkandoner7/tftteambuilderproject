package com.portal.tftteambuilderproject.ui.home

import androidx.lifecycle.ViewModel
import com.portal.tftteambuilderproject.data.ChampionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _championList = MutableStateFlow<List<ChampionItem>>(emptyList())
    val championList = _championList.asStateFlow()

    private val _selectedChampionList = MutableStateFlow<List<ChampionItem>>(emptyList())
    val selectedChampionList = _selectedChampionList.asStateFlow()

    private val _selectedOriginList = MutableStateFlow<List<String>>(emptyList())
    val selectedOriginList = _selectedOriginList.asStateFlow()


    fun setChampionList(list: List<ChampionItem>) {
        _championList.value = list
    }

    fun setSelectedChampionList(list: List<ChampionItem>) {
        _selectedChampionList.value = list
    }

    fun setSelectedOriginList(list: List<String>) {
        _selectedOriginList.value = list
    }
}
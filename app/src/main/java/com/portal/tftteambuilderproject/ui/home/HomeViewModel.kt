package com.portal.tftteambuilderproject.ui.home

import androidx.lifecycle.ViewModel
import com.portal.tftteambuilderproject.data.ChampionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _championList = MutableStateFlow<List<ChampionItem>>(emptyList())
    val championList = _championList.asStateFlow()



    fun setChampionList(list: List<ChampionItem>){
        _championList.value = list
    }
}
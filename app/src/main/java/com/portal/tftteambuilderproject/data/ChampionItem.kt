package com.portal.tftteambuilderproject.data

import java.util.Locale

data class ChampionItem(
    val name: String,
    val cost: Int,
    val origin: List<String>,
){
    fun matchesSearchQuery(searchQuery: String): Boolean {
        val lowercaseQuery = searchQuery.lowercase(Locale.getDefault())
        return name.lowercase(Locale.getDefault()).contains(lowercaseQuery) || origin.any {
            it.lowercase(Locale.getDefault()).contains(lowercaseQuery)
        }
    }
}

fun List<ChampionItem>.collectOrigins(): List<String> {
    val origins = mutableListOf<String>()
    forEach { championItem ->
        origins.addAll(championItem.origin)
    }
    return origins
}


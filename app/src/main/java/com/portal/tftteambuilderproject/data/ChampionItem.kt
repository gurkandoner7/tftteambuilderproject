package com.portal.tftteambuilderproject.data

data class ChampionItem(
    val name: String,
    val cost: Int,
    val origin: List<String>,
)

fun List<ChampionItem>.collectOrigins(): List<String> {
    val origins = mutableListOf<String>()
    forEach { championItem ->
        origins.addAll(championItem.origin)
    }
    return origins
}
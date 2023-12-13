package com.portal.tftteambuilderproject.utilities.extensions

import android.content.Context
import com.portal.tftteambuilder.R
import com.portal.tftteambuilderproject.data.ChampionItem
import org.json.JSONArray

fun Context.readChampionsFromJson(): List<ChampionItem> {
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
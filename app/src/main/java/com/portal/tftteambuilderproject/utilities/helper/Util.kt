package com.portal.tftteambuilderproject.utilities.helper

import com.portal.tftteambuilder.R
import java.util.Locale

class Util {


    enum class Champion(val championName: String, val imagePath: Int) {
        ANNIE("Annie", R.drawable.annie),
        CORKI("Corki", R.drawable.corki),
        AHRI("Ahri", R.drawable.ahri),
        AKALI_KDA("Akali K/DA", R.drawable.akali_kda),
        AMUMU("Amumu", R.drawable.amumu),
        AKALI_TRUEDAMAGE("Akali True Damage", R.drawable.akali_truedamage),
        APHELIOS("Aphelios", R.drawable.aphelios),
        BARD("Bard", R.drawable.bard),
        BLITZCRANK("Blitzcrank", R.drawable.blitzcrank),
        CAITLYN("Caitlyn", R.drawable.caitlyn),
        EKKO("Ekko", R.drawable.ekko),
        EVELYNN("Evelynn", R.drawable.evelynn),
        GAREN("Garen", R.drawable.garen),
        GNAR("Gnar", R.drawable.gnar),
        GRAGAS("Gragas", R.drawable.gragas),
        EZREAL("Ezreal", R.drawable.ezreal),
        KARTUSH("Kartush", R.drawable.kartush),
        ILLAOI("Illaoi", R.drawable.illaoi),
        KAYN("Kayn", R.drawable.kayn),
        SERAPHINE("Seraphine", R.drawable.seraphine),
        NEEKO("Neeko", R.drawable.neeko),
        ZAC("Zac", R.drawable.zac),
        KAYLE("Kayle", R.drawable.kayle),
        LUCIAN("Lucian", R.drawable.lucian),
        NAMI("Nami", R.drawable.nami),
        SAMIRA("Samira", R.drawable.samira),
        JINX("Jinx", R.drawable.jinx),
        YORICK("Yorick", R.drawable.yorick),
        VIEGO("Viego", R.drawable.viego),
        KAI_SA("Kai'sa", R.drawable.kaisa),
        LUX("Lux", R.drawable.lux),
        SONA("Sona", R.drawable.sona),
        RIVEN("Riven", R.drawable.riven),
        PANTHEON("Pantheon", R.drawable.pantheon),
        POPPY("Poppy", R.drawable.poppy),
        MORDEKAISER("Mordekaiser", R.drawable.mordekaiser),
        TWISTED_FATE("Twisted Fate", R.drawable.twistedfate),
        JAX("Jax", R.drawable.jax),
        OLAF("Olaf", R.drawable.olaf),
        K_SANTE("K'Sante", R.drawable.ksante),
        THRESH("Thresh", R.drawable.thresh),
        KENNEN("Kennen", R.drawable.kennen),
        TWITCH("Twitch", R.drawable.twitch),
        LULU("Lulu", R.drawable.lulu),
        MISS_FORTUNE("Miss Fortune", R.drawable.missfortune),
        KARTHUS("Karthus", R.drawable.kartush),
        SENNA("Senna", R.drawable.senna),
        VI("Vi", R.drawable.vi),
        TAHM_KENCH("Tahm Kench", R.drawable.tahmkench),
        URGOT("Urgot", R.drawable.urgot),
        TARIC("Taric", R.drawable.taric),
        YONE("Yone", R.drawable.yone),
        JHIN("Jhin", R.drawable.jhin),
        QIYANA("Qiyana", R.drawable.qiyana),
        KATARINA("Katarina", R.drawable.katarina),
        ZED("Zed", R.drawable.zed),
        LILLIA("Lillia", R.drawable.lillia),
        VEX("Vex", R.drawable.vex),
        ZIGGS("Ziggs", R.drawable.ziggs),
        YASUO("Yasuo", R.drawable.yasuo),
        SETT("Sett", R.drawable.sett)
    }

    enum class Origin(val feature: String, val imagePath: Int,val triggerFrequency: List<Int>) {
        EIGHT_BIT("8-Bit", R.drawable.ic_eight_bit, listOf(2,4,6)),
        COUNTRY("Country", R.drawable.ic_country, listOf(3,5,7)),
        DISCO("Disco", R.drawable.ic_disco, listOf(3,4,5,6)),
        EDM("EDM", R.drawable.ic_edm, listOf(2,3,4,5)),
        EMO("Emo", R.drawable.ic_emo, listOf(2,4,6)),
        HEARTSTEEL("Heartsteel", R.drawable.ic_heartsteel, listOf(3,5,7,10)),
        HYPERPOP("Hyperpop", R.drawable.ic_hyperpop, listOf(1,2,3,4)),
        ILLBEATS("ILLBEATS", R.drawable.ic_illbeats, listOf(1)),
        JAZZ("Jazz", R.drawable.ic_jazz, listOf(2,3,4)),
        KDA("K/DA", R.drawable.ic_kda, listOf(3,5,7,10)),
        MAESTRO("Maestro", R.drawable.ic_maestro, listOf(1)),
        MIXMASTER("Mixmaster", R.drawable.ic_mixmaster, listOf(1)),
        PENTAKILL("Pentakill", R.drawable.ic_pentakill, listOf(3,5,7,10)),
        PUNK("Punk", R.drawable.ic_punk, listOf(2,4,6)),
        TRUE_DAMAGE("True Damage", R.drawable.ic_true_damage, listOf(2,4,6,9)),
        WILDCARD("Wildcard", R.drawable.ic_wildcard, listOf(3,5,7)),
        BIG_SHOT("Big Shot", R.drawable.ic_big_shot, listOf(2,4,6)),
        BRUISER("Bruiser", R.drawable.ic_bruiser, listOf(2,4,6)),
        BREAKOUT("Breakout", R.drawable.ic_breakout, listOf(1)),
        CROWD_DIVER("Crowd Diver", R.drawable.ic_crowd_diver, listOf(2,4,6)),
        DAZZLER("Dazzler", R.drawable.ic_dazzler, listOf(2,4,6)),
        EDGELORD("Edgelord", R.drawable.ic_edgelord, listOf(3,5,7)),
        EXECUTIONER("Executioner", R.drawable.ic_executioner, listOf(2,4,6)),
        GUARDIAN("Guardian", R.drawable.ic_guardian, listOf(2,4,6)),
        MOSHER("Mosher", R.drawable.ic_mosher, listOf(2,4,6)),
        RAPIDFIRE("Rapidfire", R.drawable.ic_rapidfire, listOf(2,4,6)),
        SENTINEL("Sentinel", R.drawable.ic_sentinel, listOf(2,4,6,8)),
        SPELLWEAVER("Spellweaver", R.drawable.ic_spellweaver, listOf(3,5,7,10)),
        SUPERFAN("Superfan", R.drawable.ic_superfan, listOf(3,4,5))

    }
    companion object {
        fun countOrigins(origins: List<String>): Map<String, Int> {
            val originCounts = mutableMapOf<String, Int>().withDefault { 0 }
            origins.forEach { origin ->
                originCounts[origin] = originCounts.getValue(origin) + 1
            }
            return originCounts
        }

    const val MAGIC_KEY_FIRST = "%%%"
    const val MAGIC_KEY_SECOND = "+++"
    }


}
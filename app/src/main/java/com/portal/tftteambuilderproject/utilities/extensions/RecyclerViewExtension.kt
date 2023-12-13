package com.portal.tftteambuilderproject.utilities.extensions

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setGridLayoutSize(spanCount: Int) {
    val layoutManager = GridLayoutManager(context, spanCount)
    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return 1
        }
    }
    this.layoutManager = layoutManager
    this.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
}


package com.threecats.fastsmoothscroll

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

const val TAG = "FSS"

object FastSmoothScroll {

    var bufferScreens = 3.0

    var logTag = "FastSmoothScroll"

    var logging = false

    fun log(msg: String) {
        if (logging) {
            Log.d(logTag, msg)
        }
    }
}

fun RecyclerView.fastSmoothScrollToPosition(newPosition: Int) {
    FastSmoothScroll.log("New Position: $newPosition")
    fastScrollCloseToSmoothScrollPosition(newPosition)
    smoothScrollToPosition(newPosition)
    FastSmoothScroll.log("Smooth scroll done.")
}

private fun RecyclerView.fastScrollCloseToSmoothScrollPosition(newPosition: Int) {
    val firstVisiblePosition: Int
    val lastVisiblePosition: Int
    when (val activeLayoutManager = layoutManager) {
        is LinearLayoutManager -> {
            FastSmoothScroll.log("LayoutManager: Linear.")
            firstVisiblePosition = activeLayoutManager.findFirstVisibleItemPosition()
            lastVisiblePosition = activeLayoutManager.findLastVisibleItemPosition()
        }
        is GridLayoutManager -> {
            FastSmoothScroll.log("LayoutManager: Grid.")
            firstVisiblePosition = activeLayoutManager.findFirstVisibleItemPosition()
            lastVisiblePosition = activeLayoutManager.findLastVisibleItemPosition()
        }
        else -> {
            FastSmoothScroll.log("LayoutManager: unsupported. No fast close scroll.")
            return
        }
    }

    FastSmoothScroll.log("First: $firstVisiblePosition <-> Last: $lastVisiblePosition")

    val screenLength = lastVisiblePosition - firstVisiblePosition
    val bufferLength = (screenLength * FastSmoothScroll.bufferScreens).roundToInt()

    val currentMiddlePosition = (lastVisiblePosition + firstVisiblePosition) / 2

    FastSmoothScroll.log("Screen: $screenLength / Buffer: $bufferLength / Middle: $currentMiddlePosition")


    val closeLowerPosition = newPosition - bufferLength
    FastSmoothScroll.log("Close Lower: $closeLowerPosition")
    if (currentMiddlePosition < closeLowerPosition) {
        Log.d(TAG,"Fast Scroll to Lower.")
        scrollToPosition(closeLowerPosition)
        return
    }

    val closeHigherPosition = newPosition + bufferLength
    FastSmoothScroll.log("Close Higher: $closeHigherPosition")
    if (currentMiddlePosition > closeHigherPosition) {
        Log.d(TAG,"Fast Scroll to Higher.")
        scrollToPosition(closeHigherPosition)
        return
    }

    FastSmoothScroll.log("Fast scroll not needed, in close range.")
}

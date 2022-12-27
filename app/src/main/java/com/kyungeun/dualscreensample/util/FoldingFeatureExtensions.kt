package com.kyungeun.dualscreensample.util

import androidx.window.layout.FoldingFeature

/** Check is the [FoldingFeature] is the table-top mode. */
fun FoldingFeature.isTableTopPosture(): Boolean {
    return state == FoldingFeature.State.HALF_OPENED &&
        orientation == FoldingFeature.Orientation.HORIZONTAL
}

/** Check is the [FoldingFeature] is the book mode. */
fun FoldingFeature.isBookPosture(): Boolean {
    return state == FoldingFeature.State.HALF_OPENED &&
        orientation == FoldingFeature.Orientation.VERTICAL
}

/** Check is the [FoldingFeature] is the flat vertical mode. */
fun FoldingFeature.isFlatPostureVertical(): Boolean {
    return state == FoldingFeature.State.FLAT &&
        orientation == FoldingFeature.Orientation.VERTICAL
}

/** Check is the [FoldingFeature] is the flat horizontal mode. */
fun FoldingFeature.isFlatPostureHorizontal(): Boolean {
    return state == FoldingFeature.State.FLAT &&
        orientation == FoldingFeature.Orientation.HORIZONTAL
}

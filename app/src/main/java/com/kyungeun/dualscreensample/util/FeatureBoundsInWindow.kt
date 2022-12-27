package com.kyungeun.dualscreensample.util

import android.graphics.Rect
import android.view.View
import androidx.window.layout.DisplayFeature

/**
 * Get the bounds of the display feature translated to the View's coordinate space and current
 * position in the window. This will also include view padding in the calculations.
 */
fun getFeatureBoundsInWindow(
    displayFeature: DisplayFeature,
    view: View,
    includePadding: Boolean = true
): Rect? {
    // Adjust the location of the view in the window to be in the same coordinate space as the feature.
    val viewLocationInWindow = IntArray(2)
    view.getLocationInWindow(viewLocationInWindow)

    // Intersect the feature rectangle in window with view rectangle to clip the bounds.
    val viewRect = Rect(
        viewLocationInWindow[0], viewLocationInWindow[1],
        viewLocationInWindow[0] + view.width, viewLocationInWindow[1] + view.height
    )

    // Include padding if needed
    if (includePadding) {
        viewRect.left += view.paddingLeft
        viewRect.top += view.paddingTop
        viewRect.right -= view.paddingRight
        viewRect.bottom -= view.paddingBottom
    }

    val featureRectInView = Rect(displayFeature.bounds)
    val intersects = featureRectInView.intersect(viewRect)

    // Checks to see if the display feature overlaps with our view at all
    if ((featureRectInView.width() == 0 && featureRectInView.height() == 0) ||
        !intersects
    ) {
        return null
    }

    // Offset the feature coordinates to view coordinate space start point
    featureRectInView.offset(-viewLocationInWindow[0], -viewLocationInWindow[1])

    return featureRectInView
}

package com.kyungeun.dualscreensample.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import androidx.window.layout.WindowMetricsCalculator
import com.kyungeun.dualscreensample.databinding.ActivityMainBinding
import com.kyungeun.dualscreensample.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var windowInfoTracker: WindowInfoTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        windowInfoTracker = WindowInfoTracker.getOrCreate(this@MainActivity)

        obtainWindowMetrics()
        onWindowLayoutInfoChange()
    }

    /**
     * obtainWindowMetrics() is used to get the current window metrics and the maximum window metrics.
     * **/
    private fun obtainWindowMetrics() {
        val wmc = WindowMetricsCalculator.getOrCreate()
        val currentWM = wmc.computeCurrentWindowMetrics(this).bounds.flattenToString()
        val maximumWM = wmc.computeMaximumWindowMetrics(this).bounds.flattenToString()

        Timber.d("Current Window Metrics: $currentWM")
        Timber.d("Maximum Window Metrics: $maximumWM")
    }

    /**
     * onWindowLayoutInfoChange() is used to get the window layout info.
     **/
    private fun onWindowLayoutInfoChange() {
        // Create a new coroutine since repeatOnLifecycle is a suspend function.
        lifecycleScope.launch(Dispatchers.Main) {
            // The block passed to repeatOnLifecycle is executed when the lifecycle
            // is at least STARTED and is cancelled when the lifecycle is STOPPED.
            // It automatically restarts the block when the lifecycle is STARTED again.
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from WindowInfoTracker when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                windowInfoTracker.windowLayoutInfo(this@MainActivity)
                    .collect { value ->
                        // Update the UI when the window layout info changes
                        updateUI(value)
                    }
            }
        }
    }

    // Detects ui updates when rotating the screen
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Timber.d("onConfigurationChanged: Landscape")
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                Timber.d("onConfigurationChanged: Portrait")
            }
            else -> {
                Timber.d("onConfigurationChanged: Other")
            }
        }
    }

    private fun updateUI(newLayoutInfo: WindowLayoutInfo) {
        // Check if the device is in a foldable posture
        if (newLayoutInfo.displayFeatures.isNotEmpty()) {
            alignViewToFoldingFeatureBounds(newLayoutInfo)
            // If the device is in a dual-screen posture, the SlidingPaneLayout is slideable.
            binding.slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_UNLOCKED
        }
        // Single screen device
        else {
            binding.slidingPaneLayout.closePane()
            // If the device is in a single-screen posture, the SlidingPaneLayout is not slideable.
            binding.slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        }
    }

    private fun alignViewToFoldingFeatureBounds(newLayoutInfo: WindowLayoutInfo) {
        val foldingFeature = newLayoutInfo.displayFeatures.filterIsInstance<FoldingFeature>()
            .firstOrNull() as FoldingFeature

        val bounds = getFeatureBoundsInWindow(foldingFeature, binding.root)

        bounds?.let { rect ->
            when {
                foldingFeature.isTableTopPosture() -> { // Update the UI for a tablet posture
                    Timber.d("alignViewToFoldingFeatureBounds: TableTop Posture")
                }
                foldingFeature.isBookPosture() -> { // Update the UI for a book posture
                    Timber.d("alignViewToFoldingFeatureBounds: Book Posture")
                }
                foldingFeature.isFlatPostureVertical() -> { // Update the UI for a flat posture vertical
                    Timber.d("alignViewToFoldingFeatureBounds: Flat Posture Vertical")
                }
                foldingFeature.isFlatPostureHorizontal() -> { // Update the UI for a flat posture horizontal
                    Timber.d("alignViewToFoldingFeatureBounds: Flat Posture Horizontal")
                }
                else -> { // Unknown Posture
                    Timber.d("alignViewToFoldingFeatureBounds: Unknown Posture")
                }
            }
        }
    }
}

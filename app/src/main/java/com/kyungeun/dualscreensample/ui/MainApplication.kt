package com.kyungeun.dualscreensample.ui

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() = Timber.plant(Timber.DebugTree())
}

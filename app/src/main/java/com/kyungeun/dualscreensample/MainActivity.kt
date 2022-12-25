package com.kyungeun.dualscreensample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.window.WindowManager


class MainActivity : AppCompatActivity() {

    private lateinit var wm: WindowManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wm = WindowManager(this)
    }
}
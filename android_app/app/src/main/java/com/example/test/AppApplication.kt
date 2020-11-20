package com.example.test

import android.app.Application
import com.example.config.FlutterConfig

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FlutterConfig.warmUp("myId", this)
    }
}

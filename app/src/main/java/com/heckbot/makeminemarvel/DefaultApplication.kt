package com.heckbot.makeminemarvel

import android.app.Application

class DefaultApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyContext().initialize()
    }
}
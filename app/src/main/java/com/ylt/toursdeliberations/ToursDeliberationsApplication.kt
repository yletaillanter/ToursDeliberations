package com.ylt.toursdeliberations

import android.app.Application
import timber.log.Timber

class ToursDeliberationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
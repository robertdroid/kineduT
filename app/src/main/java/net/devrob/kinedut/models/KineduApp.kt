package net.devrob.kinedut.models

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class KineduApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}
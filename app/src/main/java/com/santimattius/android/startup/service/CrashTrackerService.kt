package com.santimattius.android.startup.service

class CrashTrackerService {

    var isInitialized: Boolean = false
        private set

    fun initialize() {
        isInitialized = true
    }
}
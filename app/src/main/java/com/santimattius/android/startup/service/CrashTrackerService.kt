package com.santimattius.android.startup.service

import android.content.Context

class CrashTrackerService {

    var isInitialized: Boolean = false
        private set

    fun initialize(context: Context): CrashTrackerService {
        isInitialized = true
        return this
    }
}
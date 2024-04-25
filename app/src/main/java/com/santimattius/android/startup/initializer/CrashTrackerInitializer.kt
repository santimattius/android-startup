package com.santimattius.android.startup.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.santimattius.android.startup.service.CrashTrackerService

class CrashTrackerInitializer : Initializer<CrashTrackerService> {

    override fun create(context: Context): CrashTrackerService {
        Log.i(this::class.simpleName, "create: CrashTrackerService created")
        return CrashTrackerService.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }

}
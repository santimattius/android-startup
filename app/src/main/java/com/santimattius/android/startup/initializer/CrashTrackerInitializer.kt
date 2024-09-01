package com.santimattius.android.startup.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.santimattius.android.koin.startup.KoinInitializer
import com.santimattius.android.startup.service.CrashTrackerService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CrashTrackerInitializer : Initializer<Unit>, KoinComponent {

    private val crashTrackerService: CrashTrackerService by inject()

    override fun create(context: Context) {
        Log.i(this::class.simpleName, "create: CrashTrackerService created")
        crashTrackerService.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(KoinInitializer::class.java)
    }

}
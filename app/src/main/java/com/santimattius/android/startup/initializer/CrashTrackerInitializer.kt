package com.santimattius.android.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.santimattius.android.startup.di.InitializerEntryPoint
import com.santimattius.android.startup.service.CrashTrackerService
import javax.inject.Inject

class CrashTrackerInitializer : Initializer<CrashTrackerService> {

    @Inject
    lateinit var service: CrashTrackerService
    override fun create(context: Context): CrashTrackerService {
        InitializerEntryPoint.resolve(context).inject(this)
        service.initialize()

        return service
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(DependencyGraphInitializer::class.java)
    }

}
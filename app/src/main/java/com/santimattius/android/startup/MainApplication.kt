package com.santimattius.android.startup

import android.app.Application
import android.util.Log
import com.santimattius.android.koin.startup.KoinModules
import com.santimattius.android.startup.service.CrashTrackerService
import com.santimattius.android.startup.service.ServiceExample
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApplication : Application(), KoinModules {

    override fun onCreate() {
        super.onCreate()
        Log.i(this::class.simpleName, "onCreate: application created")
    }

    override fun modules(): List<Module> {
        return listOf(appModule)
    }
}

val appModule = module {
    single { ServiceExample() }
    single { CrashTrackerService() }
}
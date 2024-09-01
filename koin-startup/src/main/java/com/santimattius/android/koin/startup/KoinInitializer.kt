package com.santimattius.android.koin.startup

import android.content.Context
import androidx.startup.Initializer
import com.santimattius.android.koin.startup.internal.ModuleLoader
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.lazyModules
import org.koin.core.module.Module

typealias ApplicationModules = Pair<List<Module>, List<Lazy<Module>>>

class KoinInitializer : Initializer<Unit> {

    private val moduleLoader = ModuleLoader()

    @OptIn(KoinExperimentalAPI::class)
    override fun create(context: Context) {
        val (syncModules, lazyModules) = extractModules(context)
        startKoin {
            androidContext(context)
            modules(syncModules)
            lazyModules(lazyModules)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    private fun extractModules(context: Context): ApplicationModules {
        val featureModules = moduleLoader.load()
        return if (context is KoinModules) {
            val mergedModules = context.modules() + featureModules.modules()
            val mergedLazyModules = context.lazyModules() + featureModules.lazyModules()
            mergedModules to mergedLazyModules
        } else {
            featureModules.modules() to featureModules.lazyModules()
        }
    }
}
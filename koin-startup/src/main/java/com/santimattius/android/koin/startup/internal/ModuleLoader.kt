package com.santimattius.android.koin.startup.internal

import android.util.Log
import com.santimattius.android.koin.startup.KoinModules
import org.koin.core.module.Module
import java.util.ServiceLoader

internal class ModuleLoader {

    fun load(): KoinModules = try {
        val providers = mutableListOf<KoinModules>()
        val loader = ServiceLoader.load(KoinModules::class.java, this.javaClass.classLoader)
        for (provider in loader) {
            providers.add(provider)
        }
        val modules = mutableListOf<Module>()
        val lazyModules = mutableListOf<Lazy<Module>>()
        providers.sortedBy {
            it.scope().value
        }.forEach {
            modules.addAll(it.modules())
            lazyModules.addAll(it.lazyModules())
        }
        MergedKoinModules(modules, lazyModules)
    } catch (ex: Throwable) {
        Log.e("ModuleLoader", "load: fail load service loader modules", ex)
        DefaultKoinModules
    }
}


package com.santimattius.android.feature

import com.santimattius.android.koin.startup.KoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class FeatureKoinModule : KoinModules {

    override fun modules(): List<Module> {
        return listOf(featureModule)
    }
}

private val featureModule = module {
    single { FeatureServices() }
}
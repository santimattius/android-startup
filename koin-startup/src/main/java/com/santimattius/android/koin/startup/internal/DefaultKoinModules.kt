package com.santimattius.android.koin.startup.internal

import com.santimattius.android.koin.startup.KoinModules
import org.koin.core.module.Module

internal object DefaultKoinModules : KoinModules {

    override fun modules() = emptyList<Module>()

    override fun lazyModules() = emptyList<Lazy<Module>>()
}
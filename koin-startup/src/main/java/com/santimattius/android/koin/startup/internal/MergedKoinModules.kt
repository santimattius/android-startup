package com.santimattius.android.koin.startup.internal

import com.santimattius.android.koin.startup.KoinModules
import org.koin.core.module.Module

internal class MergedKoinModules(
    private val modules: List<Module>,
    private val lazyModules: List<Lazy<Module>>
) : KoinModules {

    override fun modules() = modules

    override fun lazyModules() = lazyModules
}
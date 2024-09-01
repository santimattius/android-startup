package com.santimattius.android.koin.startup

import org.koin.core.module.Module

interface KoinModules {

    fun modules(): List<Module>

    fun lazyModules(): List<Lazy<Module>> = emptyList()

    @KoinFeatureExperimental
    fun scope() = ModuleScope.NONE
}

@KoinFeatureExperimental
enum class ModuleScope(val value: Int) {
    APP(0),
    CORE(1),
    LIBRARY(2),
    FEATURE(3),
    NONE(4),
}
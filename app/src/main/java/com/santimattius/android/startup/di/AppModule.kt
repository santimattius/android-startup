package com.santimattius.android.startup.di

import com.santimattius.android.startup.service.CrashTrackerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCrashTracker() = CrashTrackerService()
}
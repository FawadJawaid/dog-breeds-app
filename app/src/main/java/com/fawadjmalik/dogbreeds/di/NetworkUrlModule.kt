package com.fawadjmalik.dogbreeds.di

import com.fawadjmalik.dogbreeds.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Hilt NetworkUrlModule class which provides the base url to the NetworkModule.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkUrlModule {

    @Provides
    @Singleton
    fun provideUrl(): String {
        return BASE_URL
    }
}
package com.fawadjmalik.dogbreeds.di

import com.fawadjmalik.dogbreeds.data.repository.DataRepository
import com.fawadjmalik.dogbreeds.data.repository.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Hilt BindsModule class to provide data repository dependency to view models.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    @Singleton
    abstract fun bindDataRepository(dataRepository: DataRepository): DataSource
}
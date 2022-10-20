package com.fawadjmalik.dogbreeds.di

import android.content.Context
import androidx.room.Room
import com.fawadjmalik.dogbreeds.data.local.persistence.DogBreedsDatabase
import com.fawadjmalik.dogbreeds.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Hilt DbModule class which provides our database and dao dependency wherever it is required.
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DogBreedsDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries().build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: DogBreedsDatabase) =
        db.dogBreedsDao() // The reason we can implement a Dao for the database
}
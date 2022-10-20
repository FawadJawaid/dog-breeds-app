package com.fawadjmalik.dogbreeds.di

import com.fawadjmalik.dogbreeds.BuildConfig
import com.fawadjmalik.dogbreeds.data.local.LocalDataSource
import com.fawadjmalik.dogbreeds.data.local.LocalSource
import com.fawadjmalik.dogbreeds.data.local.persistence.DogBreedsDao
import com.fawadjmalik.dogbreeds.data.remote.RemoteDataSource
import com.fawadjmalik.dogbreeds.data.remote.RemoteSource
import com.fawadjmalik.dogbreeds.data.remote.service.DogBreedsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Hilt NetworkModule class which provides the retrofit, client, service and repositories dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideDogBreedsService(retrofit: Retrofit): DogBreedsApi {
        return retrofit.create(DogBreedsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: DogBreedsApi): RemoteSource {
        return RemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: DogBreedsDao): LocalSource {
        return LocalDataSource(dao)
    }
}

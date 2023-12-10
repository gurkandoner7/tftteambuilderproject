package com.portal.tftteambuilderproject.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /*    @Provides
        fun provideLocationDatabase(application: Application): LocationDatabase {
            return Room.databaseBuilder(
                application,
                LocationDatabase::class.java,
                "location_database"
            ).allowMainThreadQueries().build()
        }

        @Provides
        @Singleton
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        @Singleton
        fun provideApiService(
            okHttpClient: OkHttpClient,
            retrofit: Retrofit.Builder
        ): WeatherService =
            retrofit
                .client(okHttpClient)
                .build()
                .create(WeatherService::class.java)*/

}
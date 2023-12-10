package com.portal.tftteambuilderproject.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /*@Binds
    @Singleton
    abstract fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository*/
}

package com.portal.tftteambuilderproject.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    /*    @Binds
        abstract fun provideWeatherUseCase(weatherUseCaseImpl: WeatherUseCaseImpl): WeatherUseCase

        @Binds
        abstract fun provideLocationDbUseCase(locationDbUseCaseImpl: LocationDbUseCaseImpl): LocationDbUseCase*/
}
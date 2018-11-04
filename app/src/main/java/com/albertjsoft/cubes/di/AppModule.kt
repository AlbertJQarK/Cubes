package com.albertjsoft.cubes.di

import com.albertjsoft.cubes.app.CubesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: CubesApp) {
    @Provides
    @Singleton
    fun provideApp() = app
}
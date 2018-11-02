package com.albertjsoft.cubes.di

import com.albertjsoft.cubes.app.CubesApp
import com.albertjsoft.cubes.ui.cubes.CubeActivity
import javax.inject.Singleton

import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: CubesApp) {
    @Provides
    @Singleton fun provideApp() = app
}

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(app: CubesApp)
    fun inject(activity: CubeActivity)
}

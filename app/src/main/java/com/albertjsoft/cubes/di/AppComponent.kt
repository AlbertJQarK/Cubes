package com.albertjsoft.cubes.di

import com.albertjsoft.cubes.app.CubesApp
import com.albertjsoft.cubes.ui.cubes.CubeActivity
import javax.inject.Singleton
import dagger.Component


@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetModule::class])
interface AppComponent {
    fun inject(app: CubesApp)
    fun inject(activity: CubeActivity)
}

package com.albertjsoft.cubes.di

import com.albertjsoft.cubes.ui.cubes.CubePresenter
import com.albertjsoft.cubes.ui.cubes.CubePresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainActivityPresenter(): CubePresenter = CubePresenterImpl()
}
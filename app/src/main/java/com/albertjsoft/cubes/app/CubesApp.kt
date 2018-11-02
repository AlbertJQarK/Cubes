package com.albertjsoft.cubes.app

import android.app.Application
import com.albertjsoft.cubes.di.AppComponent
import com.albertjsoft.cubes.di.AppModule
import com.albertjsoft.cubes.di.DaggerAppComponent

class  CubesApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
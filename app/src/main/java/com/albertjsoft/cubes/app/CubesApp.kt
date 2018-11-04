package com.albertjsoft.cubes.app

import android.app.Application
import com.albertjsoft.cubes.di.AppComponent
import com.albertjsoft.cubes.di.DaggerAppComponent
import com.albertjsoft.cubes.di.NetModule


class  CubesApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().netModule(NetModule()).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        instance = this
    }

    companion object {
        lateinit var instance: CubesApp
            private set
    }
}
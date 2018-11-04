package com.albertjsoft.cubes.di

import com.albertjsoft.cubes.data.api.CubeService
import com.albertjsoft.cubes.util.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetModule{

    companion object {
        private const val BASE_URL = "BASE_URL"
    }

    @Provides
    @Singleton
    fun provideCubesService() : CubeService = createService(CubeService::class.java)

    private fun <T> createService(service: Class<T>): T {
        return provideRetrofit().create(service)
    }

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(provideOkHttpClient())
        .build()

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        // Interceptor
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy
        .LOWER_CASE_WITH_UNDERSCORES).create()

}

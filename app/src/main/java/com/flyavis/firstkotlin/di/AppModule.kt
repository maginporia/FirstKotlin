package com.flyavis.firstkotlin.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.flyavis.firstkotlin.FirstKotlinApplication
import com.flyavis.firstkotlin.data.network.Service
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
internal class AppModule {

    @Provides
    @Singleton
    fun provideContext(firstKotlinApplication: FirstKotlinApplication): Context {
        return firstKotlinApplication.applicationContext
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://data.coa.gov.tw/Service/OpenData/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        return client.build()
    }

}

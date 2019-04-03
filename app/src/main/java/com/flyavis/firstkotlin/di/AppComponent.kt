package com.flyavis.firstkotlin.di

import com.flyavis.firstkotlin.FirstKotlinApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewBuilder::class, AppModule::class])
interface AppComponent : AndroidInjector<FirstKotlinApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<FirstKotlinApplication>()
}

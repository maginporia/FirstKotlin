package com.flyavis.firstkotlin.di

import com.flyavis.firstkotlin.ui.ContentFragment
import com.flyavis.firstkotlin.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ViewBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindContentFragment(): ContentFragment

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}

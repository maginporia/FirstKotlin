package com.flyavis.firstkotlin.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flyavis.firstkotlin.ui.ContentViewModel
import com.flyavis.firstkotlin.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel::class)
    internal abstract fun bindContentViewModel(contentViewModel: ContentViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

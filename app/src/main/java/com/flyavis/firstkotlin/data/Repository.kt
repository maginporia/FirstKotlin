package com.flyavis.firstkotlin.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flyavis.firstkotlin.data.network.Service
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject internal constructor(private val service: Service) {

    fun getAnimalsAsync(key: Int): LiveData<PagedList<Animal>> {
        val sourceFactory = AnimalDataSourceFactory(service, key)
        val data = LivePagedListBuilder(
            sourceFactory, PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()
        return data
    }

}
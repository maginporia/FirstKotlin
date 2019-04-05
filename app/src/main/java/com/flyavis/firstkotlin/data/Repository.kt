package com.flyavis.firstkotlin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flyavis.firstkotlin.data.network.Service
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject internal constructor(private val service: Service) {
    private lateinit var dataSize: LiveData<Int>

    fun getAnimalsAsync(key: Int): LiveData<PagedList<Animal>> {
        val sourceFactory = AnimalDataSourceFactory(service, key)
        dataSize = Transformations.switchMap(sourceFactory.sourceLiveData) { it.getDataSize() }

        val data = LivePagedListBuilder(
            sourceFactory, PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()
        return data
    }

    fun getDataSize(): LiveData<Int> {
        return dataSize
    }
}
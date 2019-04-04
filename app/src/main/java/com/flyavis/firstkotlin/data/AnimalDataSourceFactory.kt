package com.flyavis.firstkotlin.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.flyavis.firstkotlin.data.network.Service

class AnimalDataSourceFactory(
    private val service: Service,
    private val key: Int
) : DataSource.Factory<Int, Animal>() {
    private val sourceLiveData = MutableLiveData<AnimalDataSource>()
    override fun create(): DataSource<Int, Animal> {
        val source = AnimalDataSource(service, key)
        sourceLiveData.postValue(source)
        return source
    }
}
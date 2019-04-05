package com.flyavis.firstkotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.flyavis.firstkotlin.data.Animal
import com.flyavis.firstkotlin.data.Repository
import javax.inject.Inject

class ContentViewModel
@Inject
internal constructor(private val repository: Repository) : ViewModel() {

    private lateinit var liveData: LiveData<PagedList<Animal>>

    fun setKey(key: Int) {
        liveData = repository.getAnimalsAsync(key)
    }

    fun getAnimals(): LiveData<PagedList<Animal>> {
        return liveData
    }

    fun getDataSize(): LiveData<Int> {
        return repository.getDataSize()
    }
}

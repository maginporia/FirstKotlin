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

    fun getAnimals(key: Int): LiveData<PagedList<Animal>> {
        return repository.getAnimalsAsync(key)
    }
}

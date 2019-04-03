package com.flyavis.firstkotlin.ui

import androidx.paging.PositionalDataSource
import com.flyavis.firstkotlin.data.Animal
import com.flyavis.firstkotlin.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimalDataSource @Inject
internal constructor(private val repository: Repository) : PositionalDataSource<List<Animal>>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<List<Animal>>) {

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<List<Animal>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getAnimalsAsync().await()
            // Switch to Android mainThread
            withContext(Dispatchers.Main) {
                // To get BookStore Object
                if (result.isSuccessful) {
                    // 200
                    val animals = result.body()


                } else {
                    // 400 - 500
                }
            }
        }
    }
}
package com.flyavis.firstkotlin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.flyavis.firstkotlin.data.network.HttpResult
import com.flyavis.firstkotlin.data.network.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException

class AnimalDataSource(
    private val service: Service,
    private val key: Int
) : ItemKeyedDataSource<Int, Animal>() {

    val dataSizeLiveData = MutableLiveData<Int>()

    fun getDataSize(): MutableLiveData<Int> {
        return dataSizeLiveData
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Animal>) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getData()
            // Switch to Android mainThread
            withContext(Dispatchers.Main) {
                when (result) {
                    is HttpResult.Success -> {
                        val isSuccessful = result.data.isSuccessful
                        if (isSuccessful) {
                            if (key == 0) {
                                val dogList: MutableList<Animal> = ArrayList()
                                for (animal in result.data.body()!!) {
                                    if (animal.kind == "狗") {
                                        dogList.add(animal)
                                    }
                                }
                                callback.onResult(dogList)
                            } else {
                                val catList: MutableList<Animal> = ArrayList()
                                for (animal in result.data.body()!!) {
                                    if (animal.kind == "貓") {
                                        catList.add(animal)
                                    }
                                }
                                callback.onResult(catList)
                            }
                            dataSizeLiveData.value = 0
                            dataSizeLiveData.value = result.data.body()!!.size
                            // Update layout here
                        } else {
                            // 400 - 500
                            // Handle errors here
                        }
                    }
                    is HttpResult.Error -> {
                        Timber.e(Log.getStackTraceString(result.exception))
                        if (result.exception is SocketTimeoutException) {
                            // Handle Timeout

                        } else {
                            // Handle other Exceptions
                            val message = result.exception.localizedMessage

                        }
                    }
                }

            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Animal>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Animal>) {

    }

    override fun getKey(item: Animal): Int {
        return item.id
    }


    private suspend fun getData(): HttpResult<Response<List<Animal>>> {
        return try {
            val result = service.getAnimalsAsync().await()
            HttpResult.Success(result)
        } catch (e: Throwable) {
            HttpResult.Error(e)
        }
    }

    private interface Callback {
        fun onResult(value: List<Animal>)
    }
}
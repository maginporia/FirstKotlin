package com.flyavis.firstkotlin.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.flyavis.firstkotlin.data.Animal
import com.flyavis.firstkotlin.data.Repository
import com.flyavis.firstkotlin.data.network.HttpResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

class ContentViewModel
@Inject
internal constructor(private val repository: Repository) : ViewModel() {

    fun getAnimals(): MutableLiveData<List<Animal>> {
        val liveData = MutableLiveData<List<Animal>>()

        CoroutineScope(Dispatchers.IO).launch {
            val result = getData()
            // Switch to Android mainThread
            withContext(Dispatchers.Main) {
                when (result) {
                    is HttpResult.Success -> {
                        val isSuccessful = result.data.isSuccessful
                        if (isSuccessful) {
                            // To get BookStore Object
                            liveData.value = result.data.body()

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
        return liveData
    }

    private suspend fun getData(): HttpResult<Response<List<Animal>>> {
        return try {
            val result = repository.getAnimalsAsync().await()
            HttpResult.Success(result)
        } catch (e: Throwable) {
            HttpResult.Error(e)
        }
    }
}

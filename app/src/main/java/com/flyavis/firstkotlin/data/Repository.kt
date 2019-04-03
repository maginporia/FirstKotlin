package com.flyavis.firstkotlin.data

import com.flyavis.firstkotlin.data.network.Service
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject internal constructor(private val service: Service) {

    fun getAnimalsAsync(): Deferred<Response<List<Animal>>> {
        return service.getAnimalsAsync()
    }

}
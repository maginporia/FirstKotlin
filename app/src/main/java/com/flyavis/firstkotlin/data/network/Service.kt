package com.flyavis.firstkotlin.data.network

import com.flyavis.firstkotlin.data.Animal
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("TransService.aspx?UnitId=QcbUEzN6E6DL")
    fun getAnimalsAsync(): Deferred<Response<List<Animal>>>
}
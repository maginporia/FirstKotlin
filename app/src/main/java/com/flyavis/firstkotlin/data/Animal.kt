package com.flyavis.firstkotlin.data

import com.google.gson.annotations.SerializedName

data class Animal(
    @SerializedName("animal_id") val id: Int,
    @SerializedName("animal_kind") val kind: String,
    @SerializedName("animal_sex") val sex: String,
    @SerializedName("animal_colour") val color: String,
    @SerializedName("album_file") val photo: String
) {
}
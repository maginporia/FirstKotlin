package com.flyavis.firstkotlin.ui

import android.net.Uri
import com.airbnb.epoxy.Typed2EpoxyController
import com.flyavis.firstkotlin.data.Animal
import com.flyavis.firstkotlin.dataItem

class EpoxyController : Typed2EpoxyController<List<Animal>, Int>() {
    override fun buildModels(data: List<Animal>?, i: Int) {
        if (data != null) {
            if (i == 0) {
                for (animal in data) {
                    if (animal.kind == "狗") {
                        dataItem {
                            id(animal.id)
                            gender(animal.sex)
                            photo(Uri.parse(animal.photo))
                            color(animal.color)
                        }
                    }
                }
            } else {
                for (animal in data) {
                    if (animal.kind == "貓") {
                        dataItem {
                            id(animal.id)
                            gender(animal.sex)
                            photo(Uri.parse(animal.photo))
                            color(animal.color)
                        }
                    }
                }

            }
        }
    }
}
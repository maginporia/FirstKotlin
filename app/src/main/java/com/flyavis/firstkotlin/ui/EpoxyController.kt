package com.flyavis.firstkotlin.ui

import android.net.Uri
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.flyavis.firstkotlin.DataItemBindingModel_
import com.flyavis.firstkotlin.data.Animal

class EpoxyController : PagedListEpoxyController<Animal>() {
    override fun buildItemModel(currentPosition: Int, item: Animal?): EpoxyModel<*> {
        return DataItemBindingModel_()
            .id(item!!.id)
            .gender(item.sex)
            .photo(Uri.parse(item.photo))
            .color(item.color)
                }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)


    }

}

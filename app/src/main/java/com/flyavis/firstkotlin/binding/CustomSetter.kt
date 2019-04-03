package com.flyavis.firstkotlin.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomSetter {

    @BindingAdapter("app:setImageByteArray")
    @JvmStatic
    fun loadImageByteArray(view: ImageView, bytes: ByteArray) {
        Glide
            .with(view.context)
            .load(bytes)
            .centerCrop()
            .into(view)
    }

    @BindingAdapter("app:setImageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, uri: Uri?) {
        if (uri != null) {
            Glide
                .with(view.context)
                .load(uri)
                .centerCrop()
                .into(view)
        }
    }
}

package com.flyavis.firstkotlin.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.flyavis.firstkotlin.R

object CustomSetter {

    @BindingAdapter("app:setImageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, uri: Uri?) {
        if (uri.toString() != "") {
            Glide
                .with(view.context)
                .load(uri)
                .placeholder(R.drawable.nopic)
                .error(R.drawable.nopic)
                .centerCrop()
                .into(view)
        } else {
            Glide
                .with(view.context)
                .load(R.drawable.nopic)
                .centerCrop()
                .into(view)
        }
    }
}

package com.example.mvmm.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("src")
fun loadImage(view:ImageView,@DrawableRes drawableRes:Int){
    Glide.with(view.context).load(drawableRes).into(view)
}

@BindingAdapter("imageUrl")
fun image(view: ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context).load(imageUrl).into(view)
    }
}

@BindingAdapter("imageUrlWithPath")
fun imageWithPath(view: ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
       image(view,"http://openweathermap.org/img/wn/${imageUrl}@2x.png")
    }
}
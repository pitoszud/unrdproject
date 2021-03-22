package com.velocip.unrdapp.adapters


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.velocip.unrdapp.R

@BindingAdapter("backgroundUrlToImage")
fun bindBackgroundUrlToImage(view: ImageView, imageUrl: String?){
    imageUrl?.let { imgUrl ->
        if (imgUrl.isNotEmpty()){
            Glide.with(view.context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(view)
        }
    }
}
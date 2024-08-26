package com.emanh.mp3music.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.emanh.mp3music.R

@BindingAdapter("app:isPlaying")
fun setControlImage(view: ImageView, isPlaying: Boolean) {
    view.setImageResource(if (isPlaying) R.drawable.stop else R.drawable.play)
}
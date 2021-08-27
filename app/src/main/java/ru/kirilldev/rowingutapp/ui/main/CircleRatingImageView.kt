package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CustomCircleImageViewBinding

class CircleRatingImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs) {

    private lateinit var binding: CustomCircleImageViewBinding
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.custom_circle_image_view, this)
        setupBinding()
    }

    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = CustomCircleImageViewBinding.inflate(layoutInflater, this)
    }

    fun setImage(){

    }



}
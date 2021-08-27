package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CustomCircleImageViewSmallBinding

class CircleRatingImageViewSmall @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttrs){

    private lateinit var binding: CustomCircleImageViewSmallBinding
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.custom_circle_image_view_small, this)
        setupBinding()
    }


    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = CustomCircleImageViewSmallBinding.inflate(layoutInflater, this)
    }

    fun setImage(){
    }

}
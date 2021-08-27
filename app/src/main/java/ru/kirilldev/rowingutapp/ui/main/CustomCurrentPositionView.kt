package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CustomCurrentPositionBinding

class CustomCurrentPositionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs) {

    var binding: CustomCurrentPositionBinding? = null
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.custom_current_position, this)
        setBackground()
        setupBinding()
    }


    private fun setBackground(){
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_card_training)
        background = bg
        elevation = 4f
    }

    private fun setupBinding(){
        if(binding == null){
            layoutInflater = LayoutInflater.from(context)
            binding = CustomCurrentPositionBinding.inflate(layoutInflater, this)
        }
    }

    fun setIcon(){

    }

    fun setTime(){

    }

    fun setType(){

    }

    fun setDate(){

    }
}
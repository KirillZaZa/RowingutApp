package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CardLastTrainingBinding

class CustomCardLastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs){

    lateinit var binding: CardLastTrainingBinding
    private lateinit var layoutInflater: LayoutInflater

    init {
        View.inflate(context, R.layout.card_last_training, this)
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_card_last_training)
        background = materialBg
        elevation = 2f

        setupBinding()
    }


    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = CardLastTrainingBinding.inflate(layoutInflater, this)
    }

    fun setImage(){

    }

    fun setPlace(){

    }



}
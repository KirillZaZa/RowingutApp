package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CustomRacingInfoBinding

class CustomRacingInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    lateinit var binding: CustomRacingInfoBinding
    private lateinit var layoutInflater: LayoutInflater

    init {
        View.inflate(context, R.layout.custom_racing_info, this)
        setupView()
        setupBinding()
    }


    private fun setupView() {
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_card_training)
        background = bg
        elevation = 4f
    }

    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = CustomRacingInfoBinding.inflate(layoutInflater, this)
    }

}
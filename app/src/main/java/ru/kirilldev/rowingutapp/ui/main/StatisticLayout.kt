package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.StatisticLayoutBinding

class StatisticLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs) {

    lateinit var binding: StatisticLayoutBinding
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.statistic_layout, this)
        setupBinding()
    }

    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = StatisticLayoutBinding.inflate(layoutInflater, this)
    }

    fun setItemStatistic(){

    }

    fun setItemDate(){

    }

    fun getItemSpinner(){

    }


}
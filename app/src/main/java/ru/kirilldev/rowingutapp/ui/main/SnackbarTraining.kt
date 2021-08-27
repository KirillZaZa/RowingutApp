package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.MainBottombarBinding
import ru.kirilldev.rowingutapp.databinding.TrainingSnackbarBinding

class SnackbarTraining @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): LinearLayout(context, attrs, defStyleAttrs) {



    lateinit var binding: TrainingSnackbarBinding
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.training_snackbar, this)
    }


    private fun setupBinding(){
        layoutInflater =  LayoutInflater.from(context)
        binding = TrainingSnackbarBinding.inflate(layoutInflater, this, false)
    }

}
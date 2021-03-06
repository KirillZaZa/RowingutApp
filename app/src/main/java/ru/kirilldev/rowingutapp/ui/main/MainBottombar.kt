package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.shape.MaterialShapeDrawable
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.MainBottombarBinding
import ru.kirilldev.rowingutapp.extensions.dpToPx

class MainBottombar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttributeSet: Int = 0
): ConstraintLayout(context, attrs, defStyleAttributeSet) {


    lateinit var binding: MainBottombarBinding
    private lateinit var layoutInflater: LayoutInflater

    init {
        View.inflate(context, R.layout.main_bottombar, this)
        val materialBg = ContextCompat.getDrawable(context, R.drawable.main_bottombar_background)
        elevation = context.dpToPx(4)
        background = materialBg
    }

    private fun setupBinding(){
        layoutInflater =  LayoutInflater.from(context)
        binding = MainBottombarBinding.inflate(layoutInflater, this)
    }


}
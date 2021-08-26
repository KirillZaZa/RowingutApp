package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.kirilldev.rowingutapp.R

class CustomCurrentPositionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs) {

    init{
        View.inflate(context, R.layout.custom_current_position, this)

    }


    private fun setBackground(){
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_card_training)
        background = bg
        elevation = 4f
    }

}
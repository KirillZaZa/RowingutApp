package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.kirilldev.rowingutapp.R

class CustomCardLastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs){

    init {
        View.inflate(context, R.layout.card_last_training, this)
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_card_last_training)
        background = materialBg
        elevation = 2f
    }

}
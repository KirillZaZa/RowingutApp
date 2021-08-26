package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import ru.kirilldev.rowingutapp.R

class CustomRacingInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    init {

        View.inflate(context, R.layout.custom_racing_info, this)
        setupView()
    }


    private fun setupView() {
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_card_training)
        background = bg
        elevation = 4f
    }

}
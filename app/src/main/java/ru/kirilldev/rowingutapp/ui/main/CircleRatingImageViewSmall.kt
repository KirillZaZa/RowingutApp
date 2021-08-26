package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kirilldev.rowingutapp.R

class CircleRatingImageViewSmall @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttrs){

    init{
        View.inflate(context, R.layout.custom_circle_image_view_small, this)
    }

}
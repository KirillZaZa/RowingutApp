package ru.kirilldev.rowingutapp.ui.onboarding

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kirilldev.rowingutapp.R

class OnBoardingBottombar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){

    init {
        View.inflate(context, R.layout.onboarding_bottombar, this)
    }



}

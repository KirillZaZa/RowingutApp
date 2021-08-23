package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import ru.kirilldev.rowingutapp.R

class SnackbarTraining @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): LinearLayout(context, attrs, defStyleAttrs) {

    init{
        View.inflate(context, R.layout.training_snackbar, this)
    }

}
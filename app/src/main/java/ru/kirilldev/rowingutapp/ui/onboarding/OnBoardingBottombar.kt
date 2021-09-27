package ru.kirilldev.rowingutapp.ui.onboarding

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.OnBoardingBottombarBinding

class OnBoardingBottombar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _binding by viewBinding(OnBoardingBottombarBinding::bind)

    val binding
        get() = _binding


    init {
        View.inflate(context, R.layout.on_boarding_bottombar, this)
    }

    fun setBottombarImage(page: Int){
        when(page){
            0 -> _binding.pages.setImageResource(R.drawable.ic_pages_1)
            1 -> binding.pages.setImageResource(R.drawable.ic_pages_2)
            2 -> binding.pages.setImageResource(R.drawable.ic_pages_3)
            else -> return
        }
    }


}

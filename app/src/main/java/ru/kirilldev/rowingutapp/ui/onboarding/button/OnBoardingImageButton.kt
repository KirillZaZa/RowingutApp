package ru.kirilldev.rowingutapp.ui.onboarding.button

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import ru.kirilldev.rowingutapp.R

class OnBoardingImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): AppCompatImageButton(context, attrs, defStyle){

    override fun drawableStateChanged() {
        if(isPressed){
            setBackgroundColor(resources.getColor(R.color.mainBackground, null))
        }else{
            setBackgroundResource(R.drawable.shape_button)
        }

        super.drawableStateChanged()
    }


}
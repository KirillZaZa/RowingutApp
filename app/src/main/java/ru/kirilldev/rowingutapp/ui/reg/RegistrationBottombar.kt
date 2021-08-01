package ru.kirilldev.rowingutapp.ui.reg

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import ru.kirilldev.rowingutapp.R

class RegistrationBottombar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var endX: Int = 0
    private var viewCurrent: View


    init {
        View.inflate(context, R.layout.registration_bottombar, this)
        viewCurrent = this.getChildAt(0)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        endX = this.width
        super.onSizeChanged(w, h, oldw, oldh)
    }

    fun signInAnimation(callback: (Boolean) -> Unit) {

        ViewCompat.animate(viewCurrent)
            .translationX(((endX/2).toFloat()))
            .setDuration(200)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .setListener(object : ViewPropertyAnimatorListener{
                override fun onAnimationStart(view: View?) {
                    callback(true)
                }

                override fun onAnimationEnd(view: View?) {
                }

                override fun onAnimationCancel(view: View?) {
                    callback(false)
                }

            })

    }

    fun signUpAnimation(callback: (Boolean) -> Unit) {
        ViewCompat.animate(viewCurrent)
            .translationX(0F)
            .setDuration(200)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .setListener(object : ViewPropertyAnimatorListener{
                override fun onAnimationStart(view: View?) {
                    callback(true)
                }

                override fun onAnimationEnd(view: View?) {
                }

                override fun onAnimationCancel(view: View?) {
                    callback(false)
                }

            })
    }


}
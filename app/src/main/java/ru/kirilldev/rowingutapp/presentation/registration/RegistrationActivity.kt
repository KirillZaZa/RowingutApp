package ru.kirilldev.rowingutapp.presentation.registration

import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.ActivityRegistrationBinding
import ru.kirilldev.rowingutapp.viewmodels.RegistrationData
import ru.kirilldev.rowingutapp.viewmodels.RegistrationViewModel
import ru.kirilldev.rowingutapp.viewmodels.RegistrationViewModelFactory

class RegistrationActivity : FragmentActivity() {


    private val viewModel: RegistrationViewModel by viewModels { RegistrationViewModelFactory(this) }
    private val viewBinding: ActivityRegistrationBinding by viewBinding(ActivityRegistrationBinding::bind)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        makeRevealAnimation(viewBinding.root)


        viewModel.observeState(this){
            renderSignIn(it)
        }
    }


    private fun renderSignIn(data: RegistrationData){

    }


    /**
     *
     * Reveal Animation
     *
     */
    private fun makeRevealAnimation(view: ConstraintLayout) {
        view.visibility = View.INVISIBLE

        val treeObserver = view.viewTreeObserver
        if (treeObserver.isAlive) {
            treeObserver.addOnGlobalLayoutListener {
                runRevealAnimation(view)
            }
        } else {
            view.visibility = View.VISIBLE
        }
    }

    private fun runRevealAnimation(view: ConstraintLayout) {
        val centerX = view.width / 2
        val centerY = view.height

        val endRadius = (view.width.coerceAtLeast(view.height) * 1.1).toFloat()
        val animator = ViewAnimationUtils.createCircularReveal(
            view,
            -centerX,
            centerY,
            0f,
            endRadius
        ).apply {
            duration = 700
            interpolator = AccelerateInterpolator()
        }

        view.visibility = View.VISIBLE
        animator.start()

    }


}

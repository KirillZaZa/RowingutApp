package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView

class CheckableImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : AppCompatImageView(context, attributeSet, defStyleAttrs), Checkable, View.OnClickListener {

    private var checked = false

    companion object{
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if(isChecked) View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        return drawableState
    }

    init {
        setOnClickListener(this)
    }


    override fun setChecked(check: Boolean) {
        if(checked == check) return
        checked = check
        refreshDrawableState()
    }

    override fun isChecked() = checked

    override fun toggle() {
        isChecked = !checked
    }

    override fun onClick(v: View?) {
        toggle()
    }
}